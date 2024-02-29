package org.alexv.tasktrackerapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.persistence.repository.TaskStateRepository;
import org.alexv.tasktrackerapi.service.TaskStateService;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class TaskStateServiceImpl implements TaskStateService {
    final TaskStateRepository taskStateRepository;

}
