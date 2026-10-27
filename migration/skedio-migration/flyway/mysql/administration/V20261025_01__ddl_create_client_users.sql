CREATE TABLE client_scopes (
    client_scope_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    client_uuid CHAR(36) NOT NULL,
    scope_uuid CHAR(36) NOT NULL,
    resource_path VARCHAR(256) NOT NULL,
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE  UNIQUE INDEX idx_client_scope_uuid ON client_scopes(client_uuid, scope_uuid);