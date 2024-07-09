CREATE TABLE bic_directory_entry
(
    uuid       UUID PRIMARY KEY,
    BIC        VARCHAR(9) NOT NULL,
    change_type VARCHAR(4),
    ed807_uuid UUID,
    CONSTRAINT fk_bic_directory_entry_ed807_uuid FOREIGN KEY (ed807_uuid) REFERENCES ed807 (uuid)
);