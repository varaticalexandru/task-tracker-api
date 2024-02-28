package org.alexv.tasktrackerapi.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_state")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String name;

    Integer ordinal;

    Instant createdAt = Instant.now();

    @OneToMany
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<TaskEntity> tasks = new ArrayList<>();
}
