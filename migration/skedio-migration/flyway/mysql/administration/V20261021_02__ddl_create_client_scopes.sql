CREATE TABLE scopes (
    scope_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    scope_uuid CHAR(36) NOT NULL,
    scope_name VARCHAR(256) NOT NULL,
    description VARCHAR(512) DEFAULT '',
    search VARCHAR(3000),
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE  INDEX idx_scope_uuid ON scopes(scope_uuid);