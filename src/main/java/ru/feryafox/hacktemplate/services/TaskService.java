package ru.feryafox.hacktemplate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.Task;
import ru.feryafox.hacktemplate.models.requests.Task.CreateTaskRequest;
import ru.feryafox.hacktemplate.repositories.TaskRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }


    // CRUD

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Task findTaskById(long id) {
        // Мб добавить выбрасывание исключения
        return taskRepository.findById(id).orElse(null);
    }

    // Почему не кастит в List?
//    public List<Task> findAll() {
//        return taskRepository.findAll();
//    }

    public void changeStatus(long id, String status) {

    }
}
