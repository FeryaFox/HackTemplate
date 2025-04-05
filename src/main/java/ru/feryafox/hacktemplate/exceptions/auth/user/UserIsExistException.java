package ru.feryafox.hacktemplate.exceptions.auth.user;


import ru.feryafox.hacktemplate.exceptions.auth.AuthServiceException;

public class UserIsExistException extends AuthServiceException {
    public UserIsExistException(String phoneNumber) {
        super("Пользователь с номером " + phoneNumber + " уже существует");
    }
}
