package org.alexv.tasktrackerapi.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task_state")
public class TaskState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer ordinal;
    private LocalDateTime createdAt;
    @OneToMany
    private List<Task> tasks;
}
