package org.alexv.tasktrackerapi.service;

import org.alexv.tasktrackerapi.api.dto.TaskStateDto;
import org.alexv.tasktrackerapi.api.dto.TaskStatesDto;

import java.util.Optional;

public interface TaskStateService {
    TaskStatesDto fetchTaskStates(Optional<String> searchTerm, Long projectId);

    TaskStateDto createTaskState(Long projectId, String name);
}
