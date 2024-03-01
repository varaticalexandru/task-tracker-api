package org.alexv.tasktrackerapi.mapper.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.ProjectDto;
import org.alexv.tasktrackerapi.mapper.Mapper;
import org.alexv.tasktrackerapi.persistence.entity.ProjectEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectMapper implements Mapper<ProjectEntity, ProjectDto> {

    ModelMapper modelMapper;

    @Override
    public ProjectDto mapTo(ProjectEntity projectEntity) {
        return modelMapper.map(projectEntity, ProjectDto.class);
    }

    @Override
    public ProjectEntity mapFrom(ProjectDto projectDto) {
        return modelMapper.map(projectDto, ProjectEntity.class);
    }

    @Override
    public List<ProjectDto> mapTo(List<ProjectEntity> a) {
        return a
                .stream()
                .map(this::mapTo)
                .toList();
    }

    @Override
    public List<ProjectEntity> mapFrom(List<ProjectDto> b) {
        return b
                .stream()
                .map(this::mapFrom)
                .toList();
    }
}
