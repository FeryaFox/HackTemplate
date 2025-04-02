package ru.feryafox.hacktemplate.exceptions.token;


import ru.feryafox.hacktemplate.exceptions.AuthServiceException;

public class RefreshTokenIsNotExistException extends AuthServiceException {
    public RefreshTokenIsNotExistException(String token) {
        super("Рефреш токен не найден: " + token);
    }
}
