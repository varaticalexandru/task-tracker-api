package org.alexv.tasktrackerapi.service;

import org.alexv.tasktrackerapi.api.dto.AckDto;
import org.alexv.tasktrackerapi.api.dto.ProjectDto;
import org.alexv.tasktrackerapi.api.dto.ProjectsDto;
import org.alexv.tasktrackerapi.persistence.entity.ProjectEntity;

import java.util.Optional;

public interface ProjectService {

    ProjectDto createProject(String name);

    ProjectDto editProject(Long projectId, String name);

    ProjectsDto fetchProjects(Optional<String> searchTerm);

    AckDto deleteProject(Long projectId);

    ProjectDto createOrUpdateProject(Optional<Long> optionalProjectId, Optional<String> optionalName);

    ProjectEntity getProjectOrThrow(Long projectId);
}
