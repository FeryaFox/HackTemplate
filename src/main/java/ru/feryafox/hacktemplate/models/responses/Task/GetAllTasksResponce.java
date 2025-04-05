package ru.feryafox.hacktemplate.models.responses.Task;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;
import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.enums.Priority;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ со всеми задачами")
public class GetAllTasksResponce {
    private UUID id;
    private String title;
    private Date createdAt;
    private UserResponce assignedTo;
    private Date dueDate;
    private Priority priority;
    private Status status;

    public static GetAllTasksResponce fromTask(Task task) {
        if (task == null) {
            return null;
        }

        GetAllTasksResponce response = new GetAllTasksResponce();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setCreatedAt(task.getCreatedAt());
        response.setDueDate(task.getDueDate());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());

        if (task.getAssignedTo() != null) {
            response.setAssignedTo(UserResponce.convertToUserResponse(task.getAssignedTo()));
        }

        return response;
    }
}
