CREATE TABLE client_users (
    client_user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    client_uuid CHAR(36) NOT NULL,
    user_uuid CHAR(36) NOT NULL,
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE  UNIQUE INDEX idx_client_user_uuid ON client_users(client_uuid, user_uuid);