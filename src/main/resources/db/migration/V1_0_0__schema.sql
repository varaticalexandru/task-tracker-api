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
    project_id BIGSERIAL,
    created_at TIMESTAMP,

    CONSTRAINT fk_project
        FOREIGN KEY (project_id)
            REFERENCES project (id)
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