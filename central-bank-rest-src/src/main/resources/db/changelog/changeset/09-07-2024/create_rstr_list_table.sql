CREATE TABLE rstr_list
(
    uuid                  UUID PRIMARY KEY,
    rstr                  VARCHAR(4) NOT NULL,
    rstr_date              DATE       NOT NULL,
    participant_info_uuid UUID,
    CONSTRAINT fk_rstr_list_participant_info FOREIGN KEY (participant_info_uuid) REFERENCES participant_info (uuid)
);