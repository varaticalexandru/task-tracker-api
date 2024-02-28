package org.alexv.tasktrackerapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.alexv.tasktrackerapi.persistence.entity.TaskEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskStateDto {
    Long id;

    String name;

    Integer ordinal;

    @JsonProperty("created_at")
    Instant createdAt = Instant.now();
}
