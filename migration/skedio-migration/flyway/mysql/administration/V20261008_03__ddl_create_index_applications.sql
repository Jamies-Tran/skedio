CREATE TABLE applications (
    application_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_uuid CHAR(36) UNIQUE NOT NULL,
    application_name VARCHAR(256) UNIQUE NOT NULL,
    search VARCHAR(3000),
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);