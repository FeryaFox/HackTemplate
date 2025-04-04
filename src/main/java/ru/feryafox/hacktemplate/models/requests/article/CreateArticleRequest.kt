package ru.feryafox.hacktemplate.models.requests.article

data class CreateArticleRequest(
    val title: String,
    val content: String?,
)