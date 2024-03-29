package org.alexv.tasktrackerapi.api.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.api.dto.TaskStateDto;
import org.alexv.tasktrackerapi.api.dto.TaskStatesDto;
import org.alexv.tasktrackerapi.service.TaskStateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskStateController {

    TaskStateService taskStateService;

    public static final String CREATE_TASK_STATE = "/api/projects/{project_id}/task_states";
    public static final String UPDATE_TASK_STATE = "/api/task_states/{task_state_id}";
    public static final String FETCH_TASK_STATES = "/api/projects/{project_id}/task_states";
    public static final String CHANGE_TASK_STATE_POSITION = "api/task_states/{task_state_id}/position/change";
    public static final String DELETE_TASK_STATE = "/api/projects/{project_id}/task_states/{task_state_id}";

    @GetMapping(FETCH_TASK_STATES)
    public ResponseEntity<TaskStatesDto> fetchTaskStates(
            @PathVariable(value = "project_id") Long projectId,
            @RequestParam(value = "search_term", required = false) Optional<String> searchTerm
    ) {

        return new ResponseEntity<>(taskStateService.fetchTaskStates(searchTerm, projectId), HttpStatus.OK);
    }

    @PostMapping(CREATE_TASK_STATE)
    public ResponseEntity<TaskStateDto> createTaskState(
            @PathVariable("project_id") Long projectId,
            @RequestParam("name") String name
    ) {
        return new ResponseEntity<>(taskStateService.createTaskState(projectId, name), HttpStatus.OK);
    }

    @PatchMapping(UPDATE_TASK_STATE)
    public ResponseEntity<TaskStateDto> updateTaskState(
            @PathVariable("task_state_id") Long taskStateId,
            @RequestParam("name") String name
    ) {
        return new ResponseEntity<>(taskStateService.updateTaskState(taskStateId, name), HttpStatus.OK);
    }

    @PatchMapping(CHANGE_TASK_STATE_POSITION)
    public ResponseEntity<TaskStateDto> changeTaskStatePosition(
            @PathVariable("task_state_id") Long taskStateId,
            @RequestParam("left_task_state") Optional<Long> leftTaskStateId
    ) {
        return new ResponseEntity<>(taskStateService.changeTaskStatePosition(taskStateId, leftTaskStateId), HttpStatus.OK);
    }

}
