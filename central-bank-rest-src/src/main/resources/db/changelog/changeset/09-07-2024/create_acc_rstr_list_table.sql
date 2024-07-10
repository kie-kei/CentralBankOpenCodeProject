CREATE TABLE acc_rstr_list
(
    uuid          UUID PRIMARY KEY,
    acc_rstr       VARCHAR(4) NOT NULL,
    acc_rstr_date   DATE       NOT NULL,
    successor_bic INT CHECK (successor_bic BETWEEN 100000000 AND 999999999),
    accounts_uuid UUID,
    CONSTRAINT fk_acc_rstr_list_accounts FOREIGN KEY (accounts_uuid) REFERENCES accounts (uuid)
);