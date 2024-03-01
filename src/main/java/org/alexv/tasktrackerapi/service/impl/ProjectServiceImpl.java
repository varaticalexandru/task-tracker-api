package org.alexv.tasktrackerapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.AckDto;
import org.alexv.tasktrackerapi.api.dto.ProjectDto;
import org.alexv.tasktrackerapi.api.dto.ProjectsDto;
import org.alexv.tasktrackerapi.api.exception.BadRequestException;
import org.alexv.tasktrackerapi.api.exception.NotFoundException;
import org.alexv.tasktrackerapi.mapper.Mapper;
import org.alexv.tasktrackerapi.persistence.entity.ProjectEntity;
import org.alexv.tasktrackerapi.persistence.repository.ProjectRepository;
import org.alexv.tasktrackerapi.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    Mapper<ProjectEntity, ProjectDto> projectMapper;

    public ProjectEntity getProjectOrThrow(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException(String.format("Project with id \"%s\" doesn't exist.", projectId)));
    }

    public ProjectDto createProject(String name) {

        if (name.trim().isEmpty())
            throw new BadRequestException("Project name can't be empty.");

        projectRepository.findByName(name)
                .ifPresent(anotherProject -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exists.", name));
                });

        ProjectEntity savedProject = projectRepository.saveAndFlush(
                ProjectEntity
                        .builder()
                        .name(name)
                        .build());

        return projectMapper.mapTo(savedProject);
    }

    public ProjectDto editProject(Long projectId, String name) {

        if (name.trim().isEmpty())
            throw new BadRequestException("Project name can't be empty.");

        ProjectEntity foundProject = getProjectOrThrow(projectId);

        projectRepository.findByName(name)
                .filter(anotherProject -> !Objects.equals(anotherProject.getId(), projectId))
                .ifPresent(anotherProject -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exists.", name));
                });

        foundProject.setName(name);

        ProjectEntity savedProject = projectRepository.saveAndFlush(foundProject);

        return projectMapper.mapTo(savedProject);
    }


    public ProjectsDto fetchProjects(Optional<String> searchTerm) {

        searchTerm = searchTerm.filter(name -> !name.trim().isEmpty());

        Stream<ProjectEntity> projectStream;

        projectStream = searchTerm
                .map(projectRepository::streamAllByNameIsContainingIgnoreCase)
                .orElseGet(projectRepository::streamAllBy);

        List<ProjectDto> projects = projectStream
                .map(projectMapper::mapTo)
                .toList();

        return ProjectsDto.builder()
                .projects(projects)
                .build();
    }

    public AckDto deleteProject(Long projectId) {

        getProjectOrThrow(projectId);

        projectRepository.deleteById(projectId);

        return AckDto.makeDefault(true);
    }

    public ProjectDto createOrUpdateProject(Optional<Long> optionalProjectId, Optional<String> optionalName) {

        boolean isCreate = optionalProjectId.isEmpty();

        String name = optionalName
                .filter(n -> !n.trim().isEmpty())
                .orElseThrow(() -> new BadRequestException("Project name can't be empty."));

        ProjectEntity project = optionalProjectId
                .map(this::getProjectOrThrow)
                .orElse(ProjectEntity.builder().build());

        if (isCreate) {
            projectRepository.findByName(name)
                    .ifPresent(anotherProject -> {
                        throw new BadRequestException(String.format("Project \"%s\" already exists.", name));
                    });
        } else {

            projectRepository.findByName(name)
                    .filter(anotherProject -> !Objects.equals(anotherProject.getId(), optionalProjectId.get()))
                    .ifPresent(anotherProject -> {
                        throw new BadRequestException(String.format("Project \"%s\" already exists.", name));
                    });

        }

        project.setName(name);

        ProjectEntity saved = projectRepository.saveAndFlush(project);

        return projectMapper.mapTo(saved);
    }
}
