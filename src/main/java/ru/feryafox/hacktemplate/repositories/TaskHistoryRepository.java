package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.feryafox.hacktemplate.entities.TaskHistory;

@Repository
public interface TaskHistoryRepository extends CrudRepository<TaskHistory, Long> {
}
