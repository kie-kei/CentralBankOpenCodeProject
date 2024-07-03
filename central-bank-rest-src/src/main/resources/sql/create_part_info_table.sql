-- DROP TABLE IF EXISTS part_info;
CREATE TABLE IF NOT EXISTS part_info
(
    uuid              UUID PRIMARY KEY,
    part_no           INTEGER        NOT NULL,
    part_quantity     INTEGER        NOT NULL,
    part_aggregate_id NUMERIC(27, 0) NOT NULL,
    ed807_uuid  UUID,
    CONSTRAINT fk_part_info_ed807_uuid FOREIGN KEY (ed807_uuid) REFERENCES ed807 (uuid),
    CONSTRAINT part_no_check CHECK (part_no >= 0 AND part_no <= 999999),
    CONSTRAINT part_quantity_check CHECK (part_quantity >= 0 AND part_quantity <= 999999)
);