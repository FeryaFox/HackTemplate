package ru.feryafox.hacktemplate.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private final String error;
    private final String message;

    public ErrorMessage(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public static String getJsonMessage(ErrorMessage errorMessage) {
        return "{\"error\":\"" + errorMessage.getError() + "\",\"message\":\"" + errorMessage.getMessage() + "\"}";
    }
}
