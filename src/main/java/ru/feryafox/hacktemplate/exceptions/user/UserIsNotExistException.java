package ru.feryafox.hacktemplate.exceptions.user;

import lombok.extern.slf4j.Slf4j;
import ru.feryafox.hacktemplate.exceptions.AuthServiceException;

@Slf4j
public class UserIsNotExistException extends AuthServiceException {
    public UserIsNotExistException(String phoneNumber) {
        super("Пользователь с номером " + phoneNumber + " не найден");
        log.error("Ошибка: пользователь с номером {} не найден", phoneNumber);
    }
}
