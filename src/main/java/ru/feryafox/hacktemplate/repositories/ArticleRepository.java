package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.hacktemplate.entities.Article;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
}