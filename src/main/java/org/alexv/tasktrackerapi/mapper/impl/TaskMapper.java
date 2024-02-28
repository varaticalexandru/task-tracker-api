package org.alexv.tasktrackerapi.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.TaskDto;
import org.alexv.tasktrackerapi.mapper.Mapper;
import org.alexv.tasktrackerapi.persistence.entity.TaskEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskMapper implements Mapper<TaskEntity, TaskDto> {

    final ModelMapper modelMapper;

    @Override
    public TaskDto mapTo(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity, TaskDto.class);
    }

    @Override
    public TaskEntity mapFrom(TaskDto taskDto) {
        return modelMapper.map(taskDto, TaskEntity.class);
    }

    @Override
    public List<TaskDto> mapTo(List<TaskEntity> a) {
        return a
                .stream()
                .map(this::mapTo)
                .toList();
    }

    @Override
    public List<TaskEntity> mapFrom(List<TaskDto> b) {
        return b
                .stream()
                .map(this::mapFrom)
                .toList();
    }
}
