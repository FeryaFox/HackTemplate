package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.feryafox.hacktemplate.entities.Role;
import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.enums.EventType;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.models.requests.admin.UpdateUserRequest;
import ru.feryafox.hacktemplate.models.responses.admin.GetAllUsersResponce;
import ru.feryafox.hacktemplate.repositories.RoleRepository;
import ru.feryafox.hacktemplate.repositories.UserRepository;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.*;
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

    public void updateUser(UpdateUserRequest updateUserRequest) {
        User user = baseService.getUserOrElseThrow(updateUserRequest.getId());

        user.setLogin(updateUserRequest.getLogin());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setMiddleName(updateUserRequest.getMiddleName());
        user.setSurname(updateUserRequest.getSurname());

        String roleStr = updateUserRequest.getRole();
        if (roleStr == null || roleStr.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "В запросе отсутствует роль");
        }

        Role.RoleName role;
        try {
            role = Role.RoleName.valueOf(roleStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Некорректная роль. Разрешенные значения: " + Arrays.toString(Role.RoleName.values()));
        }
        baseService.setRole(user, role);

        userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        User user = baseService.getUserOrElseThrow(id);
        user.setDeleted(true);
        user.setDeletedAt(new Date());
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
