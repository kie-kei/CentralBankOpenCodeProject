-- DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    user_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT username_not_empty CHECK (username <> ''),
    CONSTRAINT password_not_empty CHECK (password <> '')
);