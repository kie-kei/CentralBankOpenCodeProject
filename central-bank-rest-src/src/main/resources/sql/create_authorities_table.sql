-- DROP TABLE IF EXISTS authorities;
CREATE TABLE IF NOT EXISTS authorities
(
    authority_id INT AUTO_INCREMENT PRIMARY KEY,
    authority    VARCHAR(255)
);

INSERT INTO authorities (authority) VALUES ('ROLE_USER');
INSERT INTO authorities (authority) VALUES ('ROLE_ADMIN');