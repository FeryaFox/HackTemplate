package ru.feryafox.hacktemplate.models.requests.Task;

import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.enums.Status;

import java.util.UUID;

public class ChangeStatusRequest {

    private UUID id;

    private Status status;

}
