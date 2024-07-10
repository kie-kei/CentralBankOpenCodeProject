CREATE TABLE initial_ed
(
    uuid     UUID PRIMARY KEY,
    ed_no     INT    NOT NULL CHECK (ed_no BETWEEN 1 AND 999999999),
    ed_date   DATE   NOT NULL,
    ed_author BIGINT NOT NULL CHECK (ed_author BETWEEN 1000000000 AND 9999999999),
    CONSTRAINT fk_initial_ed_ed807 FOREIGN KEY (uuid) REFERENCES ed807 (uuid)
);