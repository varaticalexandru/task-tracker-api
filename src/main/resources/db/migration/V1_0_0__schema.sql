CREATE TABLE project
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(40),
    created_at TIMESTAMP
);

CREATE TABLE task_state
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(40),
    ordinal    integer,
    created_at TIMESTAMP
);

CREATE TABLE task
(
    id            BIGSERIAL PRIMARY KEY NOT NULL,
    name          VARCHAR(40),
    description   VARCHAR(512),
    task_state_id BIGSERIAL,
    created_at    TIMESTAMP,

    CONSTRAINT fk_task_state
        FOREIGN KEY (task_state_id)
            REFERENCES task_state (id)
);



CREATE TABLE project_task_states
(
    id             BIGSERIAL PRIMARY KEY NOT NULL,
    project_id     BIGSERIAL,
    task_states_id BIGSERIAL,

    CONSTRAINT fk_project
        FOREIGN KEY (project_id)
            REFERENCES project (id),

    CONSTRAINT fk_state
        FOREIGN KEY (task_states_id)
            REFERENCES task_state (id)
);



CREATE TABLE task_state_tasks
(
    id       BIGSERIAL PRIMARY KEY NOT NULL,
    task_state_id BIGSERIAL,
    tasks_id BIGSERIAL,

    CONSTRAINT fk_task_state
        FOREIGN KEY (task_state_id)
            REFERENCES task_state (id),

    CONSTRAINT fk_task
        FOREIGN KEY (tasks_id)
            REFERENCES task (id)
);