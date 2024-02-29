package org.alexv.tasktrackerapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.persistence.repository.TaskStateRepository;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class TaskStateService {
    final TaskStateRepository taskStateRepository;

}
