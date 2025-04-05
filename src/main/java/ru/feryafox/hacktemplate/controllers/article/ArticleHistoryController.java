package ru.feryafox.hacktemplate.controllers.article;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feryafox.hacktemplate.services.ArticleService;

@RestController
@RequestMapping("/articles/history/")
@RequiredArgsConstructor
public class ArticleHistoryController {
    private final ArticleService articleService;

    @GetMapping("{articleId}")
    public ResponseEntity<?> getArticleHistory(@PathVariable String articleId) {
        return ResponseEntity.ok(articleService.getArticleHistory(articleId));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllArticleHistory() {
        return ResponseEntity.ok(articleService.getAllArticleHistory());
    }
}
