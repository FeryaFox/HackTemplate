package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.feryafox.hacktemplate.entities.TaskHistory;

import java.util.UUID;

@Repository
public interface TaskHistoryRepository extends CrudRepository<TaskHistory, UUID> {
}
