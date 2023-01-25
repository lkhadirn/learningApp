BEGIN;
ALTER TABLE question
    ADD COLUMN explanation_image_normal_blob BYTEA,
    ADD COLUMN explanation_image_large_blob  BYTEA;
COMMIT;