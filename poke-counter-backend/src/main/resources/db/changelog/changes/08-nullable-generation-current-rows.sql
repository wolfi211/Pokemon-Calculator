--liquibase formatted sql

--changeset daniel.wolf:8 context:nullable_generation
--comment: generation_id nullable for current rows; one current row per (pokemon, slot) or (pokemon, stat)
ALTER TABLE pokemon_types
    ALTER COLUMN generation_id DROP NOT NULL;

CREATE UNIQUE INDEX pokemon_types_current_unique ON pokemon_types (pokemon_id, slot)
    WHERE generation_id IS NULL;

ALTER TABLE pokemon_stats
    ALTER COLUMN generation_id DROP NOT NULL;

CREATE UNIQUE INDEX pokemon_stats_current_unique ON pokemon_stats (pokemon_id, stat_id)
    WHERE generation_id IS NULL;
