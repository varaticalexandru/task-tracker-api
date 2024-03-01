package org.alexv.tasktrackerapi.mapper.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.TaskStateDto;
import org.alexv.tasktrackerapi.mapper.Mapper;
import org.alexv.tasktrackerapi.persistence.entity.TaskStateEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskStateMapper implements Mapper<TaskStateEntity, TaskStateDto> {

    ModelMapper modelMapper;

    @Autowired
    public TaskStateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        Converter<Optional<TaskStateEntity>, Long> optionalToIdConverter = new Converter<Optional<TaskStateEntity>, Long>() {
            public Long convert(MappingContext<Optional<TaskStateEntity>, Long> context) {
                return context.getSource().map(TaskStateEntity::getId).orElse(null);
            }
        };

        PropertyMap<TaskStateEntity, TaskStateDto> taskStateMap = new PropertyMap<TaskStateEntity, TaskStateDto>() {
            protected void configure() {
                using(optionalToIdConverter).map(source.getLeftTaskState(), destination.getLeftTaskStateId());
                using(optionalToIdConverter).map(source.getRightTaskState(), destination.getRightTaskStateId());
            }
        };

        modelMapper.addMappings(taskStateMap);
    }

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
