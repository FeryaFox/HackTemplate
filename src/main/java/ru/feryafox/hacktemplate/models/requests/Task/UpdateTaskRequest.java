package ru.feryafox.hacktemplate.models.requests.Task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.feryafox.hacktemplate.enums.Priority;
import ru.feryafox.hacktemplate.enums.Status;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "Запрос на редактирование задачи")
public class UpdateTaskRequest {

    private UUID id;

    private String title;

    private String description;

    private UUID assignedTo;

    private Priority priority;

    private Date dueDate;

    private Status status;

}
