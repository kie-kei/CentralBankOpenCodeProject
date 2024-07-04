-- DROP TABLE IF EXISTS accounts;
CREATE TABLE IF NOT EXISTS accounts
(
    uuid                     UUID PRIMARY KEY,
    account                  CHAR(20) NOT NULL,
    regulation_account_type  CHAR(4)  NOT NULL,
    CK                       CHAR(2),
    account_CBRBIC           CHAR(9)  NOT NULL,
    date_in                  DATE     NOT NULL,
    date_out                 DATE,
    account_status           CHAR(4),
    bic_directory_entry_uuid UUID,
    CONSTRAINT fk_bic_directory_entry_uuid FOREIGN KEY (bic_directory_entry_uuid) REFERENCES bic_directory_entry (uuid),
    CONSTRAINT account_check CHECK (length(account) = 20),
    CONSTRAINT regulation_account_type_check CHECK (length(regulation_account_type) = 4),
    CONSTRAINT CK_check CHECK (length(CK) = 2),
    CONSTRAINT account_CBRBIC_check CHECK (length(account_CBRBIC) = 9),
    CONSTRAINT account_status_check CHECK (length(account_status) = 4)
);