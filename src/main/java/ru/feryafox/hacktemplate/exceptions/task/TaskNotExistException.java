package ru.feryafox.hacktemplate.exceptions.task;

import ru.feryafox.hacktemplate.exceptions.HackException;

public class TaskNotExistException extends TaskException {
    public TaskNotExistException(String task) {
        super("Task with id " + task + " is not exist");
    }
}
