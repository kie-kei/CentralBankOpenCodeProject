CREATE TABLE ed807
(
    uuid             UUID PRIMARY KEY,
    file_name         VARCHAR(255)             NOT NULL,
    created_at        TIMESTAMP                NOT NULL,
    created_by        VARCHAR(255)             NOT NULL,
    ed_no             INT                      NOT NULL CHECK (ed_no <= 999999999),
    ed_date           DATE                     NOT NULL,
    ed_author         BIGINT                   NOT NULL CHECK (ed_author BETWEEN 1000000000 AND 9999999999),
    ed_receiver       BIGINT CHECK (ed_receiver BETWEEN 1000000000 AND 9999999999),
    creation_reason   VARCHAR(4)               NOT NULL,
    creation_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    info_type_code     VARCHAR(4)               NOT NULL,
    business_day      DATE                     NOT NULL,
    directory_version INT CHECK (directory_version BETWEEN 0 AND 99)
);