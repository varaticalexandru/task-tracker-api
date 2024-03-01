package org.alexv.tasktrackerapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskStateDto {

    Long id;

    String name;

    @JsonProperty("left_task_state_id")
    Long leftTaskStateId;

    @JsonProperty("right_task_state_id")
    Long rightTaskStateId;

    @JsonProperty("created_at")
    Instant createdAt;

    @JsonProperty("updated_at")
    Instant updatedAt;

    @JsonProperty("tasks")
    List<TaskDto> tasks;
}
