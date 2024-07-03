-- DROP TABLE IF EXISTS rstr_list;
CREATE TABLE IF NOT EXISTS rstr_list
(
    uuid                  UUID PRIMARY KEY,
    rstr                  CHAR(4) NOT NULL,
    rstr_date             DATE    NOT NULL,
    participant_info_uuid UUID,
    CONSTRAINT fk_participant_info_uuid FOREIGN KEY (participant_info_uuid) REFERENCES participant_info (uuid),
    CONSTRAINT rstr_check CHECK (length(rstr) = 4)
);