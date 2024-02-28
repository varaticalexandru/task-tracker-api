package org.alexv.tasktrackerapi.persistence.repository;

import org.alexv.tasktrackerapi.persistence.entity.TaskStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStateRepository extends JpaRepository<TaskStateEntity, Long> {
}
