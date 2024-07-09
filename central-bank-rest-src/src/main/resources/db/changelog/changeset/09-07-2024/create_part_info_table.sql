CREATE TABLE part_info
(
    uuid              UUID PRIMARY KEY,
    part_no            INT            NOT NULL CHECK (part_no <= 999999),
    part_quantity      INT            NOT NULL CHECK (part_quantity <= 999999),
    part_aggregate_id DECIMAL(27, 0) NOT NULL,
    CONSTRAINT fk_part_info_ed807 FOREIGN KEY (uuid) REFERENCES ed807 (uuid)
);