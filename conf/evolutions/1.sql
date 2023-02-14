# --- !Ups
CREATE TABLE IF NOT EXISTS wardrobe(
    id     VARCHAR(36) PRIMARY KEY NOT NULL,
    name     TEXT,
    category   TEXT
    );

# --- !Downs
DROP TABLE IF EXISTS wardrobe;