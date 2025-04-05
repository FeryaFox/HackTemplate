package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.entities.User;
import ru.feryafox.hacktemplate.exceptions.article.ArticleIsNotExistException;
import ru.feryafox.hacktemplate.exceptions.auth.user.UserIsNotExistException;
import ru.feryafox.hacktemplate.repositories.ArticleHistoryRepository;
import ru.feryafox.hacktemplate.repositories.ArticleRepository;
import ru.feryafox.hacktemplate.repositories.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleHistoryRepository articleHistoryRepository;

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
}
