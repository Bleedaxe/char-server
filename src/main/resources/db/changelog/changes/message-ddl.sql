-- liquibase formatted sql

-- changeset tsvetelin:1
CREATE TABLE message
(
    id         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    type       VARCHAR(50)  NOT NULL,
    payload    VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL
)