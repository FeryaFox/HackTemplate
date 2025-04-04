CREATE TABLE articles
(
    id              UUID    NOT NULL,
    title           VARCHAR(255),
    content         VARCHAR(255),
    image           VARCHAR(255),
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    created_user_id UUID,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_user_id UUID,
    is_deleted      BOOLEAN NOT NULL,
    deleted_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_articles PRIMARY KEY (id)
);

CREATE TABLE articles_history
(
    id              UUID NOT NULL,
    article_id      UUID,
    event_type      VARCHAR(255),
    changed_at      TIMESTAMP WITHOUT TIME ZONE,
    changed_user_id UUID,
    CONSTRAINT pk_articles_history PRIMARY KEY (id)
);

ALTER TABLE articles_history
    ADD CONSTRAINT FK_ARTICLES_HISTORY_ON_ARTICLE FOREIGN KEY (article_id) REFERENCES articles (id);

ALTER TABLE articles_history
    ADD CONSTRAINT FK_ARTICLES_HISTORY_ON_CHANGED_USER FOREIGN KEY (changed_user_id) REFERENCES users (id);

ALTER TABLE articles
    ADD CONSTRAINT FK_ARTICLES_ON_CREATED_USER FOREIGN KEY (created_user_id) REFERENCES users (id);

ALTER TABLE articles
    ADD CONSTRAINT FK_ARTICLES_ON_UPDATED_USER FOREIGN KEY (updated_user_id) REFERENCES users (id);