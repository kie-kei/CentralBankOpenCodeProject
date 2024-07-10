CREATE TABLE participant_info
(
    uuid                     UUID PRIMARY KEY,
    name_p                   VARCHAR(160) NOT NULL,
    engl_name                 VARCHAR(140),
    reg_n                    VARCHAR(9),
    cntr_cd                   VARCHAR(2),
    rgn                      VARCHAR(2)   NOT NULL,
    ind                      VARCHAR(6),
    tnp                      VARCHAR(5),
    nnp                      VARCHAR(25),
    adr                      VARCHAR(160),
    prnt_bic                 VARCHAR(9),
    date_in                   DATE         NOT NULL,
    date_out                  DATE,
    pt_type                   VARCHAR(2)   NOT NULL,
    srvcs                    VARCHAR(1)   NOT NULL,
    xch_type                  VARCHAR(1)   NOT NULL,
    uid                      VARCHAR(10)  NOT NULL,
    participant_status        VARCHAR(4),
    bic_directory_entry_uuid UUID,
    CONSTRAINT fk_participant_info_bic_directory_entry FOREIGN KEY (bic_directory_entry_uuid) REFERENCES bic_directory_entry (uuid)
);