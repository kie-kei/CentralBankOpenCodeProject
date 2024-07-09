CREATE TABLE swbics
(
    uuid                     UUID PRIMARY KEY,
    swbic                    VARCHAR(11) NOT NULL,
    default_swbic            BOOLEAN     NOT NULL,
    bic_directory_entry_uuid UUID,
    CONSTRAINT fk_swbics_bic_directory_entry FOREIGN KEY (bic_directory_entry_uuid) REFERENCES bic_directory_entry (uuid)
);