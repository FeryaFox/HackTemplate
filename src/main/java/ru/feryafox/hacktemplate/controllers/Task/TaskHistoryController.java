package ru.feryafox.hacktemplate.controllers.Task;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feryafox.hacktemplate.entities.TaskHistory;
import ru.feryafox.hacktemplate.models.responses.Task.GetTasksHistory;
import ru.feryafox.hacktemplate.services.TaskHistoryService;
import ru.feryafox.hacktemplate.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks/history/")
@RequiredArgsConstructor
public class TaskHistoryController {

    public final TaskHistoryService taskHistoryService;

    @GetMapping("")
    public List<GetTasksHistory> getTaskHistory() {
        return taskHistoryService.getTaskHistory();
    }
}
