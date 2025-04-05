package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.TaskHistory;
import ru.feryafox.hacktemplate.models.responses.Task.GetTasksHistory;
import ru.feryafox.hacktemplate.repositories.TaskHistoryRepository;
import ru.feryafox.hacktemplate.repositories.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskHistoryService {

    private final TaskHistoryRepository taskHistoryRepository;

    public List<GetTasksHistory> getTaskHistory() {
        return StreamSupport.stream(taskHistoryRepository.findAll().spliterator(), false)
                .map(GetTasksHistory::convertToGetTasksHistory)
                .collect(Collectors.toList());
    }

}
