package org.alexv.tasktrackerapi.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.TaskStateDto;
import org.alexv.tasktrackerapi.mapper.Mapper;
import org.alexv.tasktrackerapi.persistence.entity.TaskStateEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskStateMapper implements Mapper<TaskStateEntity, TaskStateDto> {

    final ModelMapper modelMapper;

    @Override
    public TaskStateDto mapTo(TaskStateEntity taskStateEntity) {
        return modelMapper.map(taskStateEntity, TaskStateDto.class);
    }

    @Override
    public TaskStateEntity mapFrom(TaskStateDto taskStateDto) {
        return modelMapper.map(taskStateDto, TaskStateEntity.class);
    }

    @Override
    public List<TaskStateDto> mapTo(List<TaskStateEntity> a) {
        return a
                .stream()
                .map(this::mapTo)
                .toList();
    }

    @Override
    public List<TaskStateEntity> mapFrom(List<TaskStateDto> b) {
        return b
                .stream()
                .map(this::mapFrom)
                .toList();
    }
}
