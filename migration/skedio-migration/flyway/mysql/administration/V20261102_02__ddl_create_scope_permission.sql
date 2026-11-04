CREATE TABLE permissions (
    permission_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    permission_uuid CHAR(36) NOT NULL,
    permission_name VARCHAR(256) NOT NULL,
    description VARCHAR(4000) DEFAULT '',
    enabled BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE  UNIQUE INDEX idx_permission_uuid ON permissions(permission_uuid);