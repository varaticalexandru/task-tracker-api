package org.alexv.tasktrackerapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.TaskStateDto;
import org.alexv.tasktrackerapi.api.dto.TaskStatesDto;
import org.alexv.tasktrackerapi.api.exception.BadRequestException;
import org.alexv.tasktrackerapi.mapper.Mapper;
import org.alexv.tasktrackerapi.persistence.entity.ProjectEntity;
import org.alexv.tasktrackerapi.persistence.entity.TaskStateEntity;
import org.alexv.tasktrackerapi.persistence.repository.TaskStateRepository;
import org.alexv.tasktrackerapi.service.ProjectService;
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
    ProjectService projectService;

    @Override
    public TaskStatesDto fetchTaskStates(Optional<String> searchTerm, Long projectId) {

        projectService.getProjectOrThrowException(projectId);

        searchTerm = searchTerm.filter(name -> !name.trim().isEmpty());

        Stream<TaskStateEntity> taskStateStream;

        taskStateStream = searchTerm
                .map(s -> taskStateRepository.streamAllByNameIsContainingIgnoreCaseAndProjectIdIs(s, projectId))
                .orElseGet(() -> taskStateRepository.streamAllByProjectIdIs(projectId));

        List<TaskStateDto> taskStates = taskStateStream
                .map(taskStateMapper::mapTo)
                .toList();

        return TaskStatesDto.builder()
                .taskStates(taskStates)
                .build();
    }

    @Override
    public TaskStateDto createTaskState(Long projectId, String name) {

        if (name.trim().isEmpty())
            throw new BadRequestException("Project name can't be empty.");

        ProjectEntity existingProject = projectService.getProjectOrThrowException(projectId);

        existingProject.getTaskStates()
                .stream()
                .map(TaskStateEntity::getName)
                .filter(taskStateName -> taskStateName.equalsIgnoreCase(name))
                .findAny()
                .ifPresent(s -> {
                    throw new BadRequestException(String.format("Task state \"%s\" already exists.", name));
                });

        TaskStateEntity newTaskState = taskStateRepository.saveAndFlush(
                TaskStateEntity
                        .builder()
                        .name(name)
                        .project(existingProject)
                        .build()
        );

        taskStateRepository
                .findTaskStateEntityByRightTaskStateIsNullAndProjectIdAndIdIsNot(projectId, newTaskState.getId())
                .ifPresent(lastTaskState -> {
                    newTaskState.setLeftTaskState(lastTaskState);
                    lastTaskState.setRightTaskState(newTaskState);

                    taskStateRepository.saveAndFlush(lastTaskState);
                });

        final TaskStateEntity savedTaskState = taskStateRepository.saveAndFlush(newTaskState);

        return taskStateMapper.mapTo(savedTaskState);
    }

    @Override
    public TaskStateDto updateTaskState(Long taskStateId, String name) {

        if (name.trim().isEmpty())
            throw new BadRequestException("Project name can't be empty.");

        TaskStateEntity existingTaskState = getTaskStateOrThrowException(taskStateId);

        ProjectEntity project = existingTaskState.getProject();

        project.getTaskStates()
                .stream()
                .filter(anotherTaskState -> anotherTaskState.getName().equalsIgnoreCase(name) && !anotherTaskState.getId().equals(taskStateId))
                .findAny()
                .ifPresent(it -> {
                    throw new BadRequestException(String.format("Task state \"%s\" already exists.", name));
                });

        existingTaskState.setName(name);

        TaskStateEntity savedTaskState = taskStateRepository.saveAndFlush(existingTaskState);

        return taskStateMapper.mapTo(savedTaskState);
    }

    @Override
    public TaskStateEntity getTaskStateOrThrowException(Long taskStateId) {
        return taskStateRepository.findById(taskStateId)
                .orElseThrow(() -> new BadRequestException(String.format("Task State with id \"%s\" doesn't exist.", taskStateId)));

    }
}
