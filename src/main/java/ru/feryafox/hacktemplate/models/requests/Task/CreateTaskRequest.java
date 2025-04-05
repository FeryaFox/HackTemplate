package ru.feryafox.hacktemplate.models.requests.Task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.feryafox.hacktemplate.enums.Priority;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
@Schema(description = "Запрос на создание задачи")
public class CreateTaskRequest {

    private String title;

    private String description;

    private UUID assignedTo;

    private Date dueDate;

    private Priority priority;

    private Status status;

}
