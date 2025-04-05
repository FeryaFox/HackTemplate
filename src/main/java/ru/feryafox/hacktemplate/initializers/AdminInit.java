package ru.feryafox.hacktemplate.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.feryafox.hacktemplate.entities.Role;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.repositories.RoleRepository;
import ru.feryafox.hacktemplate.repositories.UserRepository;
import ru.feryafox.hacktemplate.services.minio.ArticleDefaultImageService;
import ru.feryafox.hacktemplate.utils.PasswordGen;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AdminInit {
    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String username = "admin";

            if (userRepository.findByLogin(username).isEmpty()) {
                String password = PasswordGen.generatePassword(16);

                Role role = roleRepository.findByName(Role.RoleName.ROLE_ADMIN).get();

                Set<Role> roles = new HashSet<>();
                roles.add(role);

                User user = User.builder()
                        .login(username)
                        .passwordHash(passwordEncoder.encode(password))
                        .firstName("Лис")
                        .surname("Админ")
                        .middleName("Админович")
                        .roles(roles)
                        .build();

                userRepository.save(user);

                System.out.printf("Первый админ: %s\n", password);
            }
        };
    }
}
