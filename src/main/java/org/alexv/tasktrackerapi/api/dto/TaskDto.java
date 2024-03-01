package org.alexv.tasktrackerapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {
    Long id;

    String name;

    String description;

    @JsonProperty("created_at")
    Instant createdAt;

    @JsonProperty("updated_at")
    Instant updatedAt;
}
