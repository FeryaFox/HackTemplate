package ru.feryafox.hacktemplate.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.feryafox.hacktemplate.entities.RefreshToken;
import ru.feryafox.hacktemplate.entities.Role;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.exceptions.auth.token.RefreshTokenIsNotExistException;
import ru.feryafox.hacktemplate.exceptions.auth.user.UserIsExistException;
import ru.feryafox.hacktemplate.models.requests.LoginRequest;
import ru.feryafox.hacktemplate.models.requests.RegisterRequest;
import ru.feryafox.hacktemplate.models.responses.AuthResponse;
import ru.feryafox.hacktemplate.repositories.RefreshTokenRepository;
import ru.feryafox.hacktemplate.repositories.RoleRepository;
import ru.feryafox.hacktemplate.repositories.UserRepository;
import ru.feryafox.hacktemplate.utils.IpAddressUtils;
import ru.feryafox.hacktemplate.utils.auth.jwt.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BaseService baseService;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        log.info("Начало регистрации пользователя с номером: {}", registerRequest.getLogin());

        if (userRepository.existsByLogin(registerRequest.getLogin())) {
            log.warn("Ошибка регистрации: пользователь с номером {} уже существует", registerRequest.getLogin());
            throw new UserIsExistException(registerRequest.getLogin());
        }

        User user = new User();
        user.setLogin(registerRequest.getLogin());
        user.setFirstName(registerRequest.getFirstName());
        user.setSurname(registerRequest.getSurname());
        user.setMiddleName(registerRequest.getMiddleName());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));

        baseService.setRole(user, Role.RoleName.ROLE_USER);

        userRepository.save(user);

        log.info("Регистрация пользователя {} завершена успешно", registerRequest.getLogin());
    }

    @Transactional
    public AuthResponse login(LoginRequest loginRequest, HttpServletRequest request) {
        log.info("Попытка входа в систему: {}", loginRequest.getLogin());
        User user;

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            user = (User) authentication.getPrincipal();
            System.out.println(user.isDeleted());
            log.info("Вход выполнен успешно: {}", loginRequest.getLogin());
        } catch (AuthenticationException e) {
            log.error("Ошибка аутентификации пользователя {}: {}", loginRequest.getLogin(), e.getMessage());
            throw e;
        }

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());

        ru.feryafox.hacktemplate.utils.auth.jwt.dto.RefreshToken refreshTokenObj = jwtUtils.generateRefreshToken(user.getId());

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenObj.getRefreshToken());
        refreshToken.setUser(user);
        refreshToken.setIpAddress(IpAddressUtils.getClientIp(request));
        refreshToken.setExpiredAt(refreshTokenObj.getExpiredAt());

        refreshTokenRepository.save(refreshToken);

        String accessToken = jwtUtils.generateToken(user.getId(), roles);

        log.info("Токены успешно созданы для пользователя {}", loginRequest.getLogin());
        return new AuthResponse(accessToken, refreshTokenObj.getRefreshToken());
    }

    @Transactional
    public AuthResponse refreshToken(String refreshToken) {
        log.info("Обновление токена: {}", refreshToken);
        jwtUtils.validateToken(refreshToken);

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByTokenAndIsLogoutFalse(refreshToken)
                .orElseThrow(() -> new RefreshTokenIsNotExistException(refreshToken));

        User tokenOwner = refreshTokenEntity.getUser();
        List<String> roles = tokenOwner.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());

        ru.feryafox.hacktemplate.utils.auth.jwt.dto.RefreshToken newRefreshToken = jwtUtils.generateRefreshToken(tokenOwner.getId());

        refreshTokenEntity.setToken(newRefreshToken.getRefreshToken());
        refreshTokenEntity.setExpiredAt(newRefreshToken.getExpiredAt());

        refreshTokenRepository.save(refreshTokenEntity);

        log.info("Токен обновлен успешно: {}", refreshToken);
        return new AuthResponse(jwtUtils.generateToken(tokenOwner.getId(), roles), newRefreshToken.getRefreshToken());
    }

    @Transactional
    public void logout(String refreshToken) {
        log.info("Попытка выхода пользователя с токеном {}", refreshToken);

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenIsNotExistException(refreshToken));

        refreshTokenEntity.setIsLogout(true);
        refreshTokenRepository.save(refreshTokenEntity);

        log.info("Пользователь успешно вышел из системы");
    }
}
