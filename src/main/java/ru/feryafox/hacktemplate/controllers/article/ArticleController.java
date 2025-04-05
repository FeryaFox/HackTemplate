package ru.feryafox.hacktemplate.controllers.article;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.feryafox.hacktemplate.models.requests.article.CreateArticleRequest;
import ru.feryafox.hacktemplate.models.requests.article.UpdateArticleRequest;
import ru.feryafox.hacktemplate.services.ArticleService;
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails;

@RestController
@RequestMapping("/articles/")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<?> createArticle(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateArticleRequest createArticleRequest
    ) {
        return ResponseEntity.ok(articleService.createArticle(createArticleRequest, userDetails));

    }

    @PostMapping("{articleId}/upload_image")
    public ResponseEntity<?> uploadImage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String articleId,
            @RequestParam("image") MultipartFile file
    ) {
        articleService.uploadImage(userDetails, articleId, file);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @PutMapping("{articleId}")
    public ResponseEntity<?> updateArticle(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String articleId,
            @RequestBody UpdateArticleRequest createArticleRequest
    ) {
        articleService.updateArticle(articleId, createArticleRequest, userDetails);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{articleId}")
    public ResponseEntity<?> deleteArticle(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String articleId
    ) {
        articleService.deleteArticle(articleId, userDetails);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{articleId}")
    public ResponseEntity<?> getArticle(
            @PathVariable String articleId
    ) {
        return ResponseEntity.ok(articleService.getArticle(articleId));
    }

    @PostMapping("{articleId}/restore")
    public ResponseEntity<?> restoreArticle(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String articleId
    ) {
        articleService.restoreArticle(articleId, userDetails);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("get_deleted_articles")
    public ResponseEntity<?> getDeletedArticles() {
        return ResponseEntity.ok(articleService.getDeletedArticles());
    }
}
