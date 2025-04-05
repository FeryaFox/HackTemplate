package ru.feryafox.hacktemplate.services;

import org.springframework.stereotype.Service;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.models.requests.article.CreateArticleRequest;
import ru.feryafox.hacktemplate.repositories.ArticleHistoryRepository;
import ru.feryafox.hacktemplate.repositories.ArticleRepository;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.UUID;

@Service
public class ArticleService {

    private final BaseService baseService;
    private final ArticleRepository articleRepository;
    private final ArticleHistoryRepository articleHistoryRepository;

    public ArticleService(BaseService baseService,
                          ArticleRepository articleRepository,
                          ArticleHistoryRepository articleHistoryRepository) {
        this.baseService = baseService;
        this.articleRepository = articleRepository;
        this.articleHistoryRepository = articleHistoryRepository;
    }

    public void createArticle(CreateArticleRequest createArticleRequest, CustomUserDetails customUserDetails) {
        var userId = UUID.fromString(customUserDetails.getUsername());
        var user = baseService.getUserOrElseThrow(userId);

        Article article = Article.builder()
                .createdUser(user)
                .title(createArticleRequest.getTitle())
                .content(createArticleRequest.getContent())
                .updatedUser(user)
                .build();

        Article savedArticle = articleRepository.save(article);

        ArticleHistory articleHistory = ArticleHistory.builder()
                .article(savedArticle)
                .changedUser(user)
                .eventType(ArticleHistory.EventType.CREATE)
                .build();

        articleHistoryRepository.save(articleHistory);
    }
}
