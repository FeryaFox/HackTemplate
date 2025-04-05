package ru.feryafox.hacktemplate.models.requests.Task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.feryafox.hacktemplate.enums.Status;

import java.util.UUID;

@Data
@AllArgsConstructor
@Schema(description = "Запрос на смену статуса задачи")
public class ChangeTaskStatusRequest {

    private UUID id;

    private Status status;

}
