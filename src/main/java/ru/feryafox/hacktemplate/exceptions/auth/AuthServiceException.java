package ru.feryafox.hacktemplate.exceptions.auth;

import ru.feryafox.hacktemplate.exceptions.HackException;

public class AuthServiceException extends HackException {
    public AuthServiceException(String message) {
        super(message);
    }
}
