-- DROP TABLE IF EXISTS user_authority_function;
CREATE TABLE IF NOT EXISTS user_authority_function
(
    user_id      BIGINT,
    authority_id INT,
    CONSTRAINT fk_user_authority_function_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_user_authority_function_authority_id FOREIGN KEY (authority_id) REFERENCES authorities (authority_id),
    PRIMARY KEY (user_id, authority_id)
);