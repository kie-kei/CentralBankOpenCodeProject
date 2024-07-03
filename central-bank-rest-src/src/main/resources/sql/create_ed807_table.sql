-- DROP TABLE IF EXISTS ed807;
CREATE TABLE IF NOT EXISTS ed807
(
    uuid               UUID PRIMARY KEY,
    file_name          VARCHAR                  NOT NULL,
    created_at         DATE                     NOT NULL,
    created_by         VARCHAR                  NOT NULL,
    updated_at         DATE,
    updated_by         VARCHAR,
    ed_no              INTEGER                  NOT NULL,
    ed_date            DATE                     NOT NULL,
    ed_author          BIGINT                   NOT NULL,
    ed_receiver        BIGINT,
    creation_reason    CHAR(4)                  NOT NULL,
    creation_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    info_type_code     CHAR(4)                  NOT NULL,
    business_day       DATE                     NOT NULL,
    directory_version  INTEGER,
    CONSTRAINT ed_no_check CHECK (ed_no >= 0 AND ed_no <= 999999999),
    CONSTRAINT ed_author_check CHECK (ed_author >= 1000000000 AND ed_author <= 9999999999),
    CONSTRAINT ed_receiver_check CHECK (ed_receiver >= 1000000000 AND ed_receiver <= 9999999999),
    CONSTRAINT creation_reason_check CHECK (creation_reason ~ '^[A-Za-z0-9]{4}$'),
    CONSTRAINT info_type_code_check CHECK (info_type_code ~ '^[A-Za-z0-9]{4}$'),
    CONSTRAINT directory_version_check CHECK (directory_version >= 0 AND directory_version <= 99)
);