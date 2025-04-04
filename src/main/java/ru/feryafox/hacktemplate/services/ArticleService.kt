package ru.feryafox.hacktemplate.services

import org.springframework.stereotype.Service
import ru.feryafox.hacktemplate.entities.Article
import ru.feryafox.hacktemplate.models.requests.CreateArticleRequest

@Service
class ArticleService {

    fun createArticle(
        createArticleRequest: CreateArticleRequest,
        customUserDetailsService: CustomUserDetailsService
    ) {

//        val article = Article(
//            title = createArticleRequest.title,
//            content = createArticleRequest.content,
//        )
    }

}