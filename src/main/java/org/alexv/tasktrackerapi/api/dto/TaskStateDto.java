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
public class TaskStateDto {
    Long id;

    String name;

    Integer ordinal;

    @Builder.Default
    @JsonProperty("created_at")
    Instant createdAt = Instant.now();

    @Builder.Default
    @JsonProperty("updated_at")
    Instant updatedAt = Instant.now();
}
