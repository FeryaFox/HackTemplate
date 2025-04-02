package ru.feryafox.hacktemplate.exceptions.user;


import ru.feryafox.hacktemplate.exceptions.AuthServiceException;

public class UserIsExistException extends AuthServiceException {
    public UserIsExistException(String phoneNumber) {
        super("Пользователь с номером " + phoneNumber + " уже существует");
    }
}
