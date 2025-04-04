package ru.feryafox.hacktemplate.exceptions.auth.token;


import ru.feryafox.hacktemplate.exceptions.auth.AuthServiceException;

public class RefreshTokenIsNotExistException extends AuthServiceException {
    public RefreshTokenIsNotExistException(String token) {
        super("Рефреш токен не найден: " + token);
    }
}
