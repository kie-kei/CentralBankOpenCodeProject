CREATE TABLE accounts
(
    uuid                     UUID PRIMARY KEY,
    account                  VARCHAR(20) NOT NULL,
    regulation_account_type    VARCHAR(4)  NOT NULL,
    CK                       VARCHAR(2),
    account_CBRBIC           VARCHAR(9)  NOT NULL,
    date_in                   DATE        NOT NULL,
    date_out                  DATE,
    account_status            VARCHAR(4),
    bic_directory_entry_uuid UUID,
    CONSTRAINT fk_accounts_bic_directory_entry FOREIGN KEY (bic_directory_entry_uuid) REFERENCES bic_directory_entry (uuid)
);