package ru.feryafox.hacktemplate.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.models.requests.article.CreateArticleRequest;
import ru.feryafox.hacktemplate.models.requests.article.UpdateArticleRequest;
import ru.feryafox.hacktemplate.models.responses.UserResponce;
import ru.feryafox.hacktemplate.models.responses.atricle.ArticleInfoResponse;
import ru.feryafox.hacktemplate.models.responses.atricle.FullArticleInfoResponse;
import ru.feryafox.hacktemplate.repositories.ArticleHistoryRepository;
import ru.feryafox.hacktemplate.repositories.ArticleRepository;
import ru.feryafox.hacktemplate.services.minio.ArticleDefaultImageService;
import ru.feryafox.hacktemplate.services.minio.ArticleImageService;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final BaseService baseService;
    private final ArticleRepository articleRepository;
    private final ArticleImageService articleImageService;
    private final ArticleDefaultImageService articleDefaultImageService;

    @Transactional
    public String createArticle(CreateArticleRequest createArticleRequest, CustomUserDetails customUserDetails) {
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

        return savedArticle.getId().toString();
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

    @Transactional
    public void updateArticle(String articleId, UpdateArticleRequest updateArticleRequest, CustomUserDetails customUserDetails) {
        var user = baseService.getUserOrElseThrow(UUID.fromString(customUserDetails.getUsername()));
        var article = baseService.getArticleOrElseThrow(UUID.fromString(articleId));

        article.setTitle(updateArticleRequest.getTitle());
        article.setContent(updateArticleRequest.getContent());
        article.setUpdatedUser(user);
        article.setUpdatedAt(new Date());

        articleRepository.save(article);

        baseService.logArticleEvent(article, user, ArticleHistory.EventType.UPDATE);
    }

    @Transactional
    public void deleteArticle(String articleId, CustomUserDetails customUserDetails) {
        var user = baseService.getUserOrElseThrow(UUID.fromString(customUserDetails.getUsername()));
        var article = baseService.getArticleOrElseThrow(UUID.fromString(articleId));

        article.setDeletedAt(new Date());
        article.setIsDeleted(true);
        article.setUpdatedUser(user);
        article.setUpdatedAt(new Date());

        articleRepository.save(article);

        baseService.logArticleEvent(article, user, ArticleHistory.EventType.DELETE);
    }

    @Transactional
    public FullArticleInfoResponse getArticle(String articleId) {
        var article = baseService.getArticleOrElseThrow(UUID.fromString(articleId));

        return FullArticleInfoResponse.from(article, articleDefaultImageService.DEFAULT_IMAGE);
    }

    @Transactional
    public void restoreArticle(String articleId, CustomUserDetails customUserDetails) {
        var user = baseService.getUserOrElseThrow(UUID.fromString(customUserDetails.getUsername()));
        var article = baseService.getArticleOrElseThrow(UUID.fromString(articleId));

        article.setDeletedAt(null);
        article.setIsDeleted(false);
        article.setUpdatedUser(user);
        article.setUpdatedAt(new Date());

        articleRepository.save(article);

        baseService.logArticleEvent(article, user, ArticleHistory.EventType.RESTORE);
    }

    public List<ArticleInfoResponse> getDeletedArticles() {
        return ArticleInfoResponse.from(articleRepository.findByIsDeletedTrue(), articleDefaultImageService.DEFAULT_IMAGE);
    }

    public List<ArticleInfoResponse> getAllArticles() {
        return ArticleInfoResponse.from(articleRepository.findByIsDeletedFalse(), articleDefaultImageService.DEFAULT_IMAGE);
    }
}
