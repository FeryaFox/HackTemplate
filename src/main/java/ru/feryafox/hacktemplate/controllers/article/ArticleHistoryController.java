package ru.feryafox.hacktemplate.controllers.article;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles/history/")
public class ArticleHistoryController {
    @GetMapping("{articleId}")
    public ResponseEntity<?> getArticleHistory(@PathVariable String articleId) {
        return null;
    }
}
