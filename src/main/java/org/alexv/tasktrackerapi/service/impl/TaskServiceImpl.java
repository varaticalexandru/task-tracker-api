package org.alexv.tasktrackerapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.persistence.repository.TaskRepository;
import org.alexv.tasktrackerapi.service.TaskService;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    final TaskRepository taskRepository;
}
