package org.alexv.tasktrackerapi.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "task_state")
@Getter
@Setter
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

    @OneToOne
    TaskStateEntity leftTaskState;

    @OneToOne
    TaskStateEntity rightTaskState;

    Instant createdAt;

    Instant updatedAt;

    @ManyToOne
    ProjectEntity project;

    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "task_state_id", referencedColumnName = "id")
    List<TaskEntity> tasks = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        updatedAt = createdAt = Instant.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = Instant.now();
    }

    public Optional<TaskStateEntity> getLeftTaskState() {
        return Optional.ofNullable(leftTaskState);
    }

    public Optional<TaskStateEntity> getRightTaskState() {
        return Optional.ofNullable(rightTaskState);
    }
}
