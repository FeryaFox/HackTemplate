package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.feryafox.hacktemplate.entities.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
