CREATE  TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_uuid CHAR(36) UNIQUE NOT NULL,
    username VARCHAR(526) UNIQUE NOT NULL,
    password VARCHAR(100) DEFAULT '',
    first_name VARCHAR(526) NOT NULL ,
    last_name VARCHAR(526) NOT NULL,
    email VARCHAR(256) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE DEFAULT '',
    status_code VARCHAR(100) NOT NULL ,
    status_name VARCHAR(256) NOT NULL,
    search VARCHAR(3000) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_by VARCHAR(526) DEFAULT '',
    updated_by VARCHAR(526) DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_user_uuid ON users(user_uuid);