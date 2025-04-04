CREATE TABLE task
(
    id          UUID    NOT NULL,
    title       VARCHAR(255),
    description VARCHAR(255),
    image_path  VARCHAR(255),
    text        VARCHAR(255),
    created_by  UUID,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_by  UUID,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    assigned_to UUID,
    priority    VARCHAR(255),
    due_date    TIMESTAMP WITHOUT TIME ZONE,
    status      VARCHAR(255),
    is_deleted  BOOLEAN NOT NULL,
    deleted_at  TIMESTAMP WITHOUT TIME ZONE,
    deleted_by  UUID,
    CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE TABLE task_history
(
    id         UUID NOT NULL,
    changed_at TIMESTAMP WITHOUT TIME ZONE,
    old_status SMALLINT,
    new_status SMALLINT,
    task_id    UUID,
    event_type VARCHAR(255),
    changed_by UUID,
    CONSTRAINT pk_taskhistory PRIMARY KEY (id)
);

ALTER TABLE task_history
    ADD CONSTRAINT FK_TASKHISTORY_ON_CHANGED_BY FOREIGN KEY (changed_by) REFERENCES users (id);

ALTER TABLE task_history
    ADD CONSTRAINT FK_TASKHISTORY_ON_TASK FOREIGN KEY (task_id) REFERENCES task (id);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_ASSIGNED_TO FOREIGN KEY (assigned_to) REFERENCES users (id);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES users (id);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_DELETED_BY FOREIGN KEY (deleted_by) REFERENCES users (id);

ALTER TABLE task
    ADD CONSTRAINT FK_TASK_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES users (id);