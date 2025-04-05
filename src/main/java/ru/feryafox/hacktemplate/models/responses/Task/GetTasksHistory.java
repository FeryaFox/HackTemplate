package ru.feryafox.hacktemplate.models.responses.Task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.entities.TaskHistory;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.enums.EventType;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ с конкретной задачей")
public class GetTasksHistory {

    private String taskName;

    private EventType eventType;

    private UserResponce changedBy;

    private Date changedAt;

    private Status oldStatus;

    private Status newStatus;


    public static GetTasksHistory convertToGetTasksHistory(TaskHistory taskHistory) {
        if (taskHistory == null) {
            return null;
        }

        GetTasksHistory response = new GetTasksHistory();
        response.setChangedAt(taskHistory.getChangedAt());
        response.setEventType(taskHistory.getEventType());
        response.setOldStatus(taskHistory.getOldStatus());
        response.setNewStatus(taskHistory.getNewStatus());
        response.setTaskName(taskHistory.getTask().getTitle());

        if (taskHistory.getChangedBy() != null) {
            response.setChangedBy(UserResponce.convertToUserResponse(taskHistory.getChangedBy()));
        }

        return response;
    }
}
