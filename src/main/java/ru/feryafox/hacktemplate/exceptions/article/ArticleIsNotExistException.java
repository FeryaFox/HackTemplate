package ru.feryafox.hacktemplate.exceptions.article;

import ru.feryafox.hacktemplate.entities.Article;

public class ArticleIsNotExistException extends ArticleException{
    public ArticleIsNotExistException(String article) {
        super("Article with id " + article + " is not exist");
    }
}
