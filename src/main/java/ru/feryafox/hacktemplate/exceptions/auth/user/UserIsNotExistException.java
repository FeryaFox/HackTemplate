package ru.feryafox.hacktemplate.exceptions.auth.user;

import lombok.extern.slf4j.Slf4j;
import ru.feryafox.hacktemplate.exceptions.auth.AuthServiceException;

@Slf4j
public class UserIsNotExistException extends AuthServiceException {
    public UserIsNotExistException(String login) {
        super("Пользователь с номером " + login + " не найден");
        log.error("Ошибка: пользователь с номером {} не найден", login);
    }
}
