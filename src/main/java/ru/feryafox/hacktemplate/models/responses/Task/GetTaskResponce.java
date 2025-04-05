package ru.feryafox.hacktemplate.models.responses.Task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.enums.Priority;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ с конкретной задачей")
public class GetTaskResponce {
    private UUID id;
    private String title;
    private String description;
    private Priority priority;
    private Date dueDate;
    private Status status;
    private UserResponce assignedTo;
    private Date createdAt;
    private UserResponce createdBy;
    private UserResponce updatedBy;

    public static GetTaskResponce fromTask(Task task) {
        if (task == null) {
            return null;
        }

        GetTaskResponce response = new GetTaskResponce();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setPriority(task.getPriority());
        response.setDueDate(task.getDueDate());
        response.setStatus(task.getStatus());
        response.setCreatedAt(task.getCreatedAt());

        if (task.getAssignedTo() != null) {
            response.setAssignedTo(UserResponce.convertToUserResponse(task.getAssignedTo()));
        }

        if (task.getCreatedBy() != null) {
            response.setCreatedBy(UserResponce.convertToUserResponse(task.getCreatedBy()));
        }

        if (task.getUpdatedBy() != null) {
            response.setUpdatedBy(UserResponce.convertToUserResponse(task.getUpdatedBy()));
        }

        return response;
    }
}