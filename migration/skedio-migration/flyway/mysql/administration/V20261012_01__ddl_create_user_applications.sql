CREATE TABLE user_applications (
    user_application_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_uuid CHAR(36) NOT NULL ,
    application_uuid CHAR(36) NOT NULL,
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX idx_user_uuid_app_uuid ON user_applications(user_uuid, application_uuid);