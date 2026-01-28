ALTER TABLE masters
    ALTER COLUMN rating TYPE double precision
    USING rating::double precision;

ALTER TABLE masters
    ALTER COLUMN rating SET DEFAULT 5.0;
