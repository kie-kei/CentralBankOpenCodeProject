-- DROP TABLE IF EXISTS acc_rstr_list;
CREATE TABLE IF NOT EXISTS acc_rstr_list
(
    uuid          UUID PRIMARY KEY,
    acc_rstr      CHAR(4) NOT NULL,
    acc_rstr_date DATE    NOT NULL,
    successor_BIC INTEGER,
    accounts_uuid UUID,
    CONSTRAINT fk_accounts_uuid FOREIGN KEY (accounts_uuid) REFERENCES accounts (uuid),
    CONSTRAINT acc_rstr_check CHECK (length(acc_rstr) = 4),
    CONSTRAINT successor_BIC_check CHECK (successor_BIC >= 100000000 AND successor_BIC <= 999999999)
);