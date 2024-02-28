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

    @Builder.Default
    @JsonProperty("created_at")
    Instant createdAt = Instant.now();
}
