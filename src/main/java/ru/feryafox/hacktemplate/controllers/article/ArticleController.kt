package ru.feryafox.hacktemplate.controllers.article

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.feryafox.hacktemplate.models.requests.CreateArticleRequest
import ru.feryafox.hacktemplate.utils.auth.CustomUserDetails

@RestController
@RequestMapping("/articles/")
class ArticleController {
    @PostMapping("")
    fun createArticle(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody createArticleRequest: CreateArticleRequest
    ) {

    }
}