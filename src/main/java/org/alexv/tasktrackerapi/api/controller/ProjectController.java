package org.alexv.tasktrackerapi.api.controller;

import lombok.RequiredArgsConstructor;
import org.alexv.tasktrackerapi.service.impl.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    final ProjectService projectService;


}
