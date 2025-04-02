package ru.feryafox.hacktemplate.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.exceptions.user.UserIsNotExistException;
import ru.feryafox.hacktemplate.models.responses.UserProfileResponse;
import ru.feryafox.hacktemplate.repositories.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InternalService {
    private final UserRepository userRepository;

    public UserProfileResponse getBuyerProfileServiceByUser(String userId) {
        log.info("Запрос профиля пользователя с ID: {}", userId);

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> {
                    log.warn("Пользователь с ID {} не найден", userId);
                    return new UserIsNotExistException(userId);
                });

        UserProfileResponse userProfile = UserProfileResponse.builder()
                .id(user.getId().toString())
                .phone(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .middleName(user.getMiddleName())
                .build();

        log.info("Профиль пользователя {} успешно загружен", userId);
        return userProfile;
    }
}
