package org.alexv.tasktrackerapi.api.controller;

import lombok.RequiredArgsConstructor;
import org.alexv.tasktrackerapi.service.impl.TaskStateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task-states")
@RequiredArgsConstructor
public class TaskStateController {
    final TaskStateService taskStateService;


}
