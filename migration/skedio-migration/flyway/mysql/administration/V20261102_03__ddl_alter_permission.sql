CREATE TABLE scope_permissions (
    scope_permission_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    scope_uuid CHAR(36) NOT NULL,
    permission_uuid CHAR(36) NOT NULL,
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE  UNIQUE INDEX idx_scope_permission_uuid ON scope_permissions(scope_uuid, permission_uuid);