package ru.feryafox.hacktemplate.models.requests.Task;

import ru.feryafox.hacktemplate.enums.Priority;
import ru.feryafox.hacktemplate.enums.Status;

import java.util.UUID;

public class EditTaskRequest {

    private UUID id;

    private String title;

    private String description;

    private String imagePath;

    private String text;

    // Добавить UserDTO с { name, surname, middleName }
    // private UserDTO assignedToUserDTO;

    private Priority priority;

    private Status status;

}
