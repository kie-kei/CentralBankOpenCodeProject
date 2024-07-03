-- DROP TABLE IF EXISTS bic_directory_entry;
CREATE TABLE IF NOT EXISTS bic_directory_entry
(
    uuid             UUID PRIMARY KEY,
    BIC              CHAR(9) NOT NULL,
    change_type      CHAR(4),
    ed807_uuid UUID,
    CONSTRAINT fk_bic_directory_entry_ed807_uuid FOREIGN KEY (ed807_uuid) REFERENCES ed807 (uuid)
);