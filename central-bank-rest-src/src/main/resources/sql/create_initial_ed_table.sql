-- DROP TABLE IF EXISTS initial_ed;
CREATE TABLE IF NOT EXISTS initial_ed
(
    uuid             UUID PRIMARY KEY,
    ed_no            INTEGER       NOT NULL,
    ed_date          DATE          NOT NULL,
    ed_author        BIGINT UNIQUE NOT NULL,
    ed807_uuid UUID,
    CONSTRAINT fk_initial_ed_ed807_uuid FOREIGN KEY (ed807_uuid) REFERENCES ed807 (uuid),
    CONSTRAINT initial_ed_ed_no_check CHECK (ed_no >= 1 AND ed_no <= 999999999),
    CONSTRAINT initial_ed_ed_author_check CHECK (ed_author >= 1000000000 AND ed_author <= 9999999999)
);