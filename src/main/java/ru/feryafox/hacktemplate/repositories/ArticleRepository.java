package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.hacktemplate.entities.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
    List<Article> findByIsDeletedFalse();
    List<Article> findByIsDeletedTrue();
}