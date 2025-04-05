package ru.feryafox.hacktemplate.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HackException extends RuntimeException {
    public HackException(String message) {
        super(message);
        log.error("Ошибка AuthService: {}", message);
    }
}
