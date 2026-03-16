--liquibase formatted sql

--changeset daniel.wolf:8 context:nullable_generation
--comment: generation_id nullable for current rows; one current row per (pokemon, slot) or (pokemon, stat). Surrogate id on pokemon_types so PK can be dropped for nullable generation_id.

-- pokemon_types: add surrogate id, drop composite PK, make generation_id nullable, add new PK and partial unique
CREATE SEQUENCE pokemon_types_id_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE pokemon_types ADD COLUMN id INTEGER NOT NULL DEFAULT nextval('pokemon_types_id_seq');
ALTER TABLE pokemon_types ALTER COLUMN id DROP DEFAULT;

ALTER TABLE pokemon_types DROP CONSTRAINT pokemon_types_pkey;

ALTER TABLE pokemon_types ALTER COLUMN generation_id DROP NOT NULL;

ALTER TABLE pokemon_types ADD PRIMARY KEY (id);
ALTER TABLE pokemon_types ALTER COLUMN id SET DEFAULT nextval('pokemon_types_id_seq');

CREATE UNIQUE INDEX pokemon_types_current_unique ON pokemon_types (pokemon_id, slot)
    WHERE generation_id IS NULL;

-- pokemon_stats: generation_id is not in PK, so just drop NOT NULL
ALTER TABLE pokemon_stats
    ALTER COLUMN generation_id DROP NOT NULL;

CREATE UNIQUE INDEX pokemon_stats_current_unique ON pokemon_stats (pokemon_id, stat_id)
    WHERE generation_id IS NULL;
