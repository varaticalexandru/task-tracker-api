package org.alexv.tasktrackerapi.api.controller;

import lombok.RequiredArgsConstructor;
import org.alexv.tasktrackerapi.api.dto.AckDto;
import org.alexv.tasktrackerapi.api.dto.ProjectDto;
import org.alexv.tasktrackerapi.api.dto.ProjectsDto;
import org.alexv.tasktrackerapi.service.ProjectService;
import org.alexv.tasktrackerapi.service.impl.ProjectServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    final ProjectService projectService;

    public static final String CREATE_PROJECT = "/api/projects";
    public static final String UPDATE_PROJECT = "/api/projects/{project_id}";
    public static final String CREATE_OR_UPDATE_PROJECT = "/api/projects";
    public static final String FETCH_PROJECTS = "/api/projects";
    public static final String DELETE_PROJECT = "/api/projects/{project_id}";

    @GetMapping(FETCH_PROJECTS)
    public ResponseEntity<ProjectsDto> fetchProjects(
            @RequestParam(value = "search_term", required = false) Optional<String> searchTerm
    ) {

        return new ResponseEntity<>(projectService.fetchProjects(searchTerm), HttpStatus.OK);
    }

    @PostMapping(CREATE_PROJECT)
    public ResponseEntity<ProjectDto> createProject(@RequestParam("name") String name) {
        return new ResponseEntity<>(projectService.createProject(name), HttpStatus.CREATED);
    }

    @PatchMapping(UPDATE_PROJECT)
    public ResponseEntity<ProjectDto> editProject(
            @PathVariable("project_id") Long projectId,
            @RequestParam("name") String name
    ) {
        return new ResponseEntity<>(projectService.editProject(projectId, name), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_PROJECT)
    public ResponseEntity<AckDto> deleteProject(@PathVariable("project_id") Long projectId) {

        return new ResponseEntity<>(projectService.deleteProject(projectId), HttpStatus.OK);
    }

    @PutMapping(CREATE_OR_UPDATE_PROJECT)
    public ResponseEntity<ProjectDto> createOrUpdateProject(
            @RequestParam(value = "project_id", required = false) Optional<Long> optionalProjectId,
            @RequestParam(value = "name", required = false) Optional<String> optionalName
    ) {

        HttpStatus status = HttpStatus.CREATED;
        if (optionalProjectId.isPresent())
            status = HttpStatus.OK;

        return new ResponseEntity<>(projectService.createOrUpdateProject(optionalProjectId, optionalName), status);
    }


}
