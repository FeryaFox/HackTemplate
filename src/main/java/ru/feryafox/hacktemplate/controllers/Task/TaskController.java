package ru.feryafox.hacktemplate.controllers.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.feryafox.hacktemplate.models.requests.Task.CreateTaskRequest;
import ru.feryafox.hacktemplate.models.requests.Task.UpdateTaskRequest;
import ru.feryafox.hacktemplate.models.responses.Task.GetAllTasksResponce;
import ru.feryafox.hacktemplate.models.responses.Task.GetTaskResponce;
import ru.feryafox.hacktemplate.services.TaskService;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tasks/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        taskService.createTask(createTaskRequest, userDetails);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("updateTask")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest,
                                        @AuthenticationPrincipal CustomUserDetails userDetails) {
        taskService.updateTask(updateTaskRequest, userDetails);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public List<GetAllTasksResponce> getAllTasks(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return taskService.findAll();
    }

    @GetMapping("{id}")
    public GetTaskResponce getTaskById(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @PathVariable UUID id) {
        return taskService.findTaskById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        taskService.deleteTask(id, userDetails);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("changeTaskStatus/{id}")
    public ResponseEntity<?> changeStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, String> requestBody,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        taskService.changeTaskStatus(id, requestBody, userDetails);
        return ResponseEntity.noContent().build();
    }

}
