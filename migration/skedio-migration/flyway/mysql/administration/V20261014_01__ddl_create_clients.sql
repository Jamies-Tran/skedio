CREATE TABLE clients (
    client_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    application_uuid CHAR(36) NOT NULL,
    client_uuid CHAR(36) UNIQUE NOT NULL,
    client_name VARCHAR(256) NOT NULL,
    description VARCHAR(512) DEFAULT '',
    search VARCHAR(3000),
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_client_application_uuid ON clients(application_uuid);
CREATE  INDEX idx_client_uuid ON clients(client_uuid);