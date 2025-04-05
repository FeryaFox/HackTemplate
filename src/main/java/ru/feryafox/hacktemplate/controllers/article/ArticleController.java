package ru.feryafox.hacktemplate.controllers.article;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.feryafox.hacktemplate.models.requests.article.CreateArticleRequest;
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
        articleService.createArticle(createArticleRequest, userDetails);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{articleId}/upload_image")
    public ResponseEntity<?> uploadImage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String articleId,
            @RequestParam("image") MultipartFile file
    ) {
        articleService.uploadImage(userDetails, articleId, file);
        return ResponseEntity.noContent().build();
    }
}
