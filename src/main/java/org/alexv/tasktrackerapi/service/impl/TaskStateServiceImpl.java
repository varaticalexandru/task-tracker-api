package org.alexv.tasktrackerapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.ProjectDto;
import org.alexv.tasktrackerapi.api.dto.ProjectsDto;
import org.alexv.tasktrackerapi.api.dto.TaskStateDto;
import org.alexv.tasktrackerapi.api.dto.TaskStatesDto;
import org.alexv.tasktrackerapi.mapper.Mapper;
import org.alexv.tasktrackerapi.mapper.impl.TaskStateMapper;
import org.alexv.tasktrackerapi.persistence.entity.ProjectEntity;
import org.alexv.tasktrackerapi.persistence.entity.TaskStateEntity;
import org.alexv.tasktrackerapi.persistence.repository.TaskStateRepository;
import org.alexv.tasktrackerapi.service.TaskStateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class TaskStateServiceImpl implements TaskStateService {

    TaskStateRepository taskStateRepository;
    Mapper<TaskStateEntity, TaskStateDto> taskStateMapper;

    @Override
    public TaskStatesDto fetchTaskStates(Optional<String> searchTerm) {

        searchTerm = searchTerm.filter(name -> !name.trim().isEmpty());

        Stream<TaskStateEntity> taskStateStream;

        taskStateStream = searchTerm
                .map(taskStateRepository::streamAllByNameIsContainingIgnoreCase)
                .orElseGet(taskStateRepository::streamAllBy);

        List<TaskStateDto> taskStates = taskStateStream
                .map(taskStateMapper::mapTo)
                .toList();

        return TaskStatesDto.builder()
                .taskStates(taskStates)
                .build();
    }
}
