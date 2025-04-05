package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    List<User> findByIsDeletedFalse();
}