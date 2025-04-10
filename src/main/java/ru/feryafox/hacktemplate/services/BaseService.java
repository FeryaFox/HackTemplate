package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.*;
import ru.feryafox.hacktemplate.enums.EventType;
import ru.feryafox.hacktemplate.enums.Status;
import ru.feryafox.hacktemplate.exceptions.article.ArticleIsNotExistException;
import ru.feryafox.hacktemplate.exceptions.auth.user.UserIsNotExistException;
import ru.feryafox.hacktemplate.exceptions.task.TaskNotExistException;
import ru.feryafox.hacktemplate.repositories.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleHistoryRepository articleHistoryRepository;
    private final TaskRepository taskRepository;
    private final TaskHistoryRepository taskHistoryRepository;
    private final RoleRepository roleRepository;

    public User getUserOrElseThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserIsNotExistException(userId.toString()));
    }

    public Article getArticleOrElseThrow(UUID articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleIsNotExistException(articleId.toString()));
    }

    @Transactional
    public void logArticleEvent(Article article, User user, ArticleHistory.EventType event) {
        ArticleHistory history = ArticleHistory.builder()
                .article(article)
                .changedUser(user)
                .eventType(event)
                .build();
        articleHistoryRepository.save(history);
    }

    public Task getTaskOrElseThrow(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotExistException(taskId.toString()));
    }

    @Transactional
    public void logTaskEvent(Task task, User user, Status oldStatus, Status newStatus, EventType event) {
        TaskHistory taskHistory = TaskHistory.builder()
                .task(task)
                .eventType(event)
                .changedBy(user)
                .oldStatus(oldStatus)
                .newStatus(newStatus)
                .build();

        taskHistoryRepository.save(taskHistory);
    }

    @Transactional
    public void setRole(User user, Role.RoleName role) {
        Role roleE = roleRepository.findByName(role).get();

        Set<Role> roles = new HashSet<>();
        roles.add(roleE);

        user.setRoles(roles);
    }
}
