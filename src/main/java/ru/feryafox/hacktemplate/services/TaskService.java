package ru.feryafox.hacktemplate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.entities.TaskHistory;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.models.requests.Task.CreateTaskRequest;
import ru.feryafox.hacktemplate.models.requests.Task.EditTaskRequest;
import ru.feryafox.hacktemplate.models.responses.Task.GetAllTasksResponce;
import ru.feryafox.hacktemplate.models.responses.Task.GetTaskResponce;
import ru.feryafox.hacktemplate.repositories.TaskHistoryRepository;
import ru.feryafox.hacktemplate.repositories.TaskRepository;
import ru.feryafox.hacktemplate.repositories.UserRepository;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
public class TaskService {
    private final BaseService baseService;
    private final TaskRepository taskRepository;
    private final TaskHistoryRepository taskHistoryRepository;
    private final UserRepository userRepository;

    // CRUD

    public void createTask(CreateTaskRequest createTaskRequest,
                           CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        // Вынести в Base Service
        User assignedUser = userRepository.findById(createTaskRequest.getAssignedTo()).get();

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


        TaskHistory taskHistory = TaskHistory.builder()
                .task(savedTask)
                .eventType(TaskHistory.EventType.CREATE)
                .changedBy(user)
                .oldStatus(createTaskRequest.getStatus())
                .newStatus(createTaskRequest.getStatus())
                .build();

        taskHistoryRepository.save(taskHistory);
    }

    public void updateTask(EditTaskRequest editTaskRequest,
                           CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        // Вынести в Base Service
        var taskToEdit = taskRepository.findById(editTaskRequest.getId()).get();

        Status oldStatus = taskToEdit.getStatus();

        // Вынести в Base Service
        User assignedUser = userRepository.findById(editTaskRequest.getAssignedTo()).get();

        taskToEdit.setTitle(editTaskRequest.getTitle());
        taskToEdit.setDescription(editTaskRequest.getDescription());
        taskToEdit.setUpdatedBy(user);
        taskToEdit.setUpdatedAt(new Date());
        taskToEdit.setAssignedTo(assignedUser);
        taskToEdit.setPriority(editTaskRequest.getPriority());
        taskToEdit.setDueDate(editTaskRequest.getDueDate());
        taskToEdit.setStatus(editTaskRequest.getStatus());

        Task savedTask = taskRepository.save(taskToEdit);

        TaskHistory taskHistory = TaskHistory.builder()
                .task(savedTask)
                .eventType(TaskHistory.EventType.UPDATE)
                .changedBy(user)
                .oldStatus(oldStatus)
                .newStatus(editTaskRequest.getStatus())
                .build();

        taskHistoryRepository.save(taskHistory);
    }

    public void deleteTask(UUID id, CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        // Перенести в Base Service
        Task taskToDelete = taskRepository.findById(id).get();

        taskToDelete.setDeleted(true);
        taskToDelete.setDeletedAt(new Date());
        taskToDelete.setDeletedBy(user);

        TaskHistory taskHistory = TaskHistory.builder()
                .task(taskToDelete)
                .eventType(TaskHistory.EventType.DELETE)
                .changedBy(user)
                .oldStatus(taskToDelete.getStatus())
                // Что здесь при удалении происходит со статусом. Он не меняется?
                .newStatus(taskToDelete.getStatus())
                .build();

        taskHistoryRepository.save(taskHistory);
    }

    public GetTaskResponce findTaskById(UUID id) {
        // Вынести в Base Service
        return GetTaskResponce.fromTask(taskRepository.findById(id).get());
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

        // Вынести в Base Service
        Task task = taskRepository.findById(id).get();

        Status oldStatus = task.getStatus();

        task.setStatus(statusEnum);

        Task savedTask = taskRepository.save(task);

        TaskHistory taskHistory = TaskHistory.builder()
                .task(savedTask)
                .eventType(TaskHistory.EventType.UPDATE)
                .changedBy(user)
                .oldStatus(oldStatus)
                .newStatus(statusEnum)
                .build();

        taskHistoryRepository.save(taskHistory);
    }

}
