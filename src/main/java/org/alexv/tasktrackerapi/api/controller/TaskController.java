package org.alexv.tasktrackerapi.api.controller;

import lombok.RequiredArgsConstructor;
import org.alexv.tasktrackerapi.service.TaskService;
import org.alexv.tasktrackerapi.service.impl.TaskServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    final TaskService taskService;


}
