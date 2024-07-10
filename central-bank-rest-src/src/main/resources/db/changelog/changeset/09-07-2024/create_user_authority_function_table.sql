CREATE TABLE user_authority_function
(
    user_id      BIGINT,
    authority_id INT,
    PRIMARY KEY (user_id, authority_id),
    CONSTRAINT fk_user_authority_function_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_user_authority_function_authority FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
);