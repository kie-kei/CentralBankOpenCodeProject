-- DROP TABLE IF EXISTS swbics;
CREATE TABLE IF NOT EXISTS swbics
(
    uuid                     UUID PRIMARY KEY,
    swbic                    VARCHAR(11) NOT NULL,
    default_swbic            BOOLEAN     NOT NULL,
    bic_directory_entry_uuid UUID,
    CONSTRAINT fk_swbics__bic_directory_entry_uuid FOREIGN KEY (bic_directory_entry_uuid) REFERENCES bic_directory_entry (uuid),
    CONSTRAINT swbic_check CHECK (length(swbic) >= 1 AND length(swbic) <= 11)
);