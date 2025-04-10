package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.enums.EventType;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.models.requests.Task.CreateTaskRequest;
import ru.feryafox.hacktemplate.models.requests.Task.UpdateTaskRequest;
import ru.feryafox.hacktemplate.models.responses.Task.GetAllTasksResponce;
import ru.feryafox.hacktemplate.models.responses.Task.GetTaskResponce;
import ru.feryafox.hacktemplate.repositories.TaskRepository;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final BaseService baseService;
    private final TaskRepository taskRepository;

    // CRUD
    public void createTask(CreateTaskRequest createTaskRequest,
                           CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        User assignedUser = baseService.getUserOrElseThrow(createTaskRequest.getAssignedTo());

        Task task = Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .createdBy(user)
                .updatedBy(user)
                .assignedTo(assignedUser)
                .priority(createTaskRequest.getPriority())
                .dueDate(createTaskRequest.getDueDate())
                .status(createTaskRequest.getStatus())
                .build();

        Task savedTask = taskRepository.save(task);

        Status oldStatus = createTaskRequest.getStatus();
        Status newStatus = createTaskRequest.getStatus();
        baseService.logTaskEvent(savedTask, user, oldStatus, newStatus, EventType.CREATE);
    }

    public void updateTask(UpdateTaskRequest updateTaskRequest,
                           CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        Task taskToEdit = baseService.getTaskOrElseThrow(updateTaskRequest.getId());

        Status oldStatus = taskToEdit.getStatus();

        User assignedUser = baseService.getUserOrElseThrow(updateTaskRequest.getAssignedTo());

        taskToEdit.setTitle(updateTaskRequest.getTitle());
        taskToEdit.setDescription(updateTaskRequest.getDescription());
        taskToEdit.setUpdatedBy(user);
        taskToEdit.setUpdatedAt(new Date());
        taskToEdit.setAssignedTo(assignedUser);
        taskToEdit.setPriority(updateTaskRequest.getPriority());
        taskToEdit.setDueDate(updateTaskRequest.getDueDate());
        taskToEdit.setStatus(updateTaskRequest.getStatus());

        Task savedTask = taskRepository.save(taskToEdit);

        Status newStatus = updateTaskRequest.getStatus();
        baseService.logTaskEvent(savedTask, user, oldStatus, newStatus, EventType.UPDATE);
    }

    public void deleteTask(UUID id, CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        Task taskToDelete = baseService.getTaskOrElseThrow(id);

        taskToDelete.setDeleted(true);
        taskToDelete.setDeletedAt(new Date());
        taskToDelete.setDeletedBy(user);

        Status oldStatus = taskToDelete.getStatus();
        Status newStatus = taskToDelete.getStatus();
        baseService.logTaskEvent(taskToDelete, user, oldStatus, newStatus, EventType.DELETE);
    }

    public GetTaskResponce findTaskById(UUID id) {
        return GetTaskResponce.fromTask(baseService.getTaskOrElseThrow(id));
    }

    public List<GetAllTasksResponce> findAll() {
        return StreamSupport.stream(taskRepository.findByIsDeletedFalse().spliterator(), false)
                .map(GetAllTasksResponce::fromTask)
                .collect(Collectors.toList());
    }

    public void changeTaskStatus(UUID id, Map<String, String> status, CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        String statusStr = status.get("status");
        if (statusStr == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "В запросе отсутствует статус");
        }

        Status statusEnum;
        try {
            statusEnum = Status.valueOf(statusStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Неккоректный статус. Разрешенные значения: " + Arrays.toString(Status.values()));
        }

        Task task = baseService.getTaskOrElseThrow(id);

        Status oldStatus = task.getStatus();

        task.setStatus(statusEnum);

        Task savedTask = taskRepository.save(task);

        baseService.logTaskEvent(savedTask, user, oldStatus, statusEnum, EventType.STATUS_CHANGE);

    }

}
