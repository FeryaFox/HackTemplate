package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.hacktemplate.entities.ArticleHistory;

import java.util.UUID;

public interface ArticleHistoryRepository extends JpaRepository<ArticleHistory, UUID> {
}