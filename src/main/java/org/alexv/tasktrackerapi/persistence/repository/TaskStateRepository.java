package org.alexv.tasktrackerapi.persistence.repository;

import org.alexv.tasktrackerapi.persistence.entity.ProjectEntity;
import org.alexv.tasktrackerapi.persistence.entity.TaskStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface TaskStateRepository extends JpaRepository<TaskStateEntity, Long> {
    Optional<TaskStateEntity> findByName(String name);

    Stream<TaskStateEntity> streamAllBy();

    Stream<TaskStateEntity> streamAllByNameIsContainingIgnoreCase(String name);
}
