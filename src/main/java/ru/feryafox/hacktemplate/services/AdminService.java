package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.Role;
import ru.feryafox.hacktemplate.models.responses.admin.GetAllUsersResponce;
import ru.feryafox.hacktemplate.repositories.RoleRepository;
import ru.feryafox.hacktemplate.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final BaseService baseService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public List<GetAllUsersResponce> getAllUsers() {
        return userRepository.findAll().stream()
                .map(GetAllUsersResponce::fromUser)
                .collect(Collectors.toList());
    }

    @Transactional
    public void setUserAdmin(String userId) {
        var user = baseService.getUserOrElseThrow(UUID.fromString(userId));

        baseService.setRole(user, Role.RoleName.ROLE_ADMIN);

        userRepository.save(user);
    }

    @Transactional
    public void setPassword(String userId, String password) {
        var user = baseService.getUserOrElseThrow(UUID.fromString(userId));

        user.setPasswordHash(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    @Transactional
    public void setRole(String userId, String role) {
        var user = baseService.getUserOrElseThrow(UUID.fromString(userId));

        baseService.setRole(user, Role.RoleName.fromString(role));

        userRepository.save(user);
    }
}
