package ru.feryafox.hacktemplate.models.requests

data class CreateArticleRequest(
    val title: String,
    val content: String?,
)