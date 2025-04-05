package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.models.requests.article.CreateArticleRequest;
import ru.feryafox.hacktemplate.models.responses.UserResponce;
import ru.feryafox.hacktemplate.models.responses.atricle.ArticleInfoResponse;
import ru.feryafox.hacktemplate.repositories.ArticleHistoryRepository;
import ru.feryafox.hacktemplate.repositories.ArticleRepository;
import ru.feryafox.hacktemplate.services.minio.ArticleDefaultImageService;
import ru.feryafox.hacktemplate.services.minio.ArticleImageService;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    private final BaseService baseService;
    private final ArticleRepository articleRepository;
    private final ArticleHistoryRepository articleHistoryRepository;
    private final ArticleImageService articleImageService;
    private final ArticleDefaultImageService articleDefaultImageService;

    public ArticleService(BaseService baseService,
                          ArticleRepository articleRepository,
                          ArticleHistoryRepository articleHistoryRepository, ArticleImageService articleImageService, ArticleDefaultImageService articleDefaultImageService) {
        this.baseService = baseService;
        this.articleRepository = articleRepository;
        this.articleHistoryRepository = articleHistoryRepository;
        this.articleImageService = articleImageService;
        this.articleDefaultImageService = articleDefaultImageService;
    }

    @Transactional
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

        baseService.logArticleEvent(article, user, ArticleHistory.EventType.CREATE);
    }

    @Transactional
    public void uploadImage(CustomUserDetails customUserDetails, String articleId, MultipartFile file) {
        var user = baseService.getUserOrElseThrow(UUID.fromString(customUserDetails.getUsername()));
        var article = baseService.getArticleOrElseThrow(UUID.fromString(articleId));

        var imageUrl = articleImageService.uploadImage(file);

        article.setImage(imageUrl);

        article.setUpdatedUser(user);
        article.setUpdatedAt(new Date());

        articleRepository.save(article);

        baseService.logArticleEvent(article, user, ArticleHistory.EventType.UPDATE);
    }

    public List<ArticleInfoResponse> getAllArticles() {
        return ArticleInfoResponse.from(articleRepository.findByIsDeletedFalse(), articleDefaultImageService.DEFAULT_IMAGE);
    }
}
