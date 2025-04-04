package ru.feryafox.hacktemplate.models.responses.Task;


import ru.feryafox.hacktemplate.enums.Priority;

import java.util.Date;
import java.util.UUID;

public class GetAllTasksResponce {

    private UUID id;

    private String title;

    private Date createdAt;

    // Добавить DTO User
   // private UserDTO userDTO

    private Date dueDate;

    private Priority priority;

}
