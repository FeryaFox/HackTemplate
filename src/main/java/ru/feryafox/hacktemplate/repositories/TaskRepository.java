package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.Task;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {
    List<Task> findByIsDeletedFalse();
}
