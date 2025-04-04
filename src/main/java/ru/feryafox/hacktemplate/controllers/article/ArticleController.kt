package ru.feryafox.hacktemplate.controllers.article

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.feryafox.hacktemplate.models.requests.article.CreateArticleRequest
import ru.feryafox.hacktemplate.services.ArticleService
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails

@RestController
@RequestMapping("/articles/")
class ArticleController(private val articleService: ArticleService) {
    @PostMapping("")
    fun createArticle(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody createArticleRequest: CreateArticleRequest
    ): ResponseEntity<Any> {
        articleService.createArticle(createArticleRequest, userDetails)
        return ResponseEntity.noContent().build()
    }
}