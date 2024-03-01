package org.alexv.tasktrackerapi.api.controller;

import lombok.RequiredArgsConstructor;
import org.alexv.tasktrackerapi.api.dto.ProjectsDto;
import org.alexv.tasktrackerapi.api.dto.TaskStatesDto;
import org.alexv.tasktrackerapi.service.TaskStateService;
import org.alexv.tasktrackerapi.service.impl.TaskStateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/task-states")
@RequiredArgsConstructor
public class TaskStateController {
    final TaskStateService taskStateService;

    public static final String CREATE_TASK_STATE = "/api/projects/{project_id}/task_states";
    public static final String UPDATE_TASK_STATE = "/api/projects/{project_id}/task_states/{task_state_id}";
    public static final String CREATE_OR_UPDATE_TASK_STATE = "/api/projects/{project_id}/task_states";
    public static final String FETCH_TASK_STATES = "/api/projects/{project_id}/task_states";
    public static final String DELETE_TASK_STATE = "/api/projects/{project_id}/task_states/{task_state_id}";

    @GetMapping(FETCH_TASK_STATES)
    public ResponseEntity<TaskStatesDto> fetchTaskStates(
            @RequestParam(value = "search_term", required = false) Optional<String> searchTerm
    ) {

        return new ResponseEntity<>(taskStateService.fetchTaskStates(searchTerm), HttpStatus.OK);
    }

}
