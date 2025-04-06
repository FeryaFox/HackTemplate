package ru.feryafox.hacktemplate.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.feryafox.hacktemplate.entities.Role;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.repositories.UserRepository;
import ru.feryafox.hacktemplate.services.BaseService;
import ru.feryafox.hacktemplate.utils.PasswordGen;

@Configuration
public class AdminInit {
    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder, BaseService baseService) {
        return args -> {
            String username = "admin";

            if (userRepository.findByLogin(username).isEmpty()) {
                String password = PasswordGen.generatePassword(16);

                User user = new User();

                baseService.setRole(user, Role.RoleName.ROLE_ADMIN);

                user.setLogin(username);
                user.setPasswordHash(passwordEncoder.encode(password));
                user.setFirstName("Лис");
                user.setSurname("Админ");
                user.setMiddleName("Админович");

                userRepository.save(user);

                System.out.printf("Первый админ: %s\n", password);
            }
        };
    }
}
