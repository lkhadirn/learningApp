BEGIN;
ALTER TABLE question
    ADD COLUMN image_normal_blob BYTEA,
    ADD COLUMN image_large_blob  BYTEA;

DROP TABLE images;
COMMIT;