--liquibase formatted sql

--changeset daniel.wolf:baseline-04-relations context:baseline
--comment: Baseline - relations/junction tables and constraints

-- Sequences for sequence-backed relation tables (JPA assigns ids; no defaults in SQL)
CREATE SEQUENCE type_relations_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokemon_ability_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokemon_moves_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokemon_stats_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokemon_types_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE type_relations
(
    id          INTEGER PRIMARY KEY,
    damage_from INTEGER REFERENCES types (id),
    damage_to   INTEGER REFERENCES types (id),
    multiplier  DECIMAL(3, 1),
    generation  INTEGER REFERENCES generations (id)
);

CREATE UNIQUE INDEX type_relations_unique ON type_relations (damage_from, damage_to, generation)
    WHERE generation IS NOT NULL;
CREATE UNIQUE INDEX type_relations_current_unique ON type_relations (damage_from, damage_to)
    WHERE generation IS NULL;

CREATE TABLE pokemon_ability
(
    id            INTEGER PRIMARY KEY,
    pokemon_id    INTEGER REFERENCES pokemon (id),
    ability_id    INTEGER REFERENCES ability (id),
    is_hidden     BOOLEAN,
    slot          INTEGER,
    generation_id INTEGER REFERENCES generations (id)
);

CREATE UNIQUE INDEX pokemon_ability_generation_unique ON pokemon_ability (pokemon_id, slot, generation_id)
    WHERE generation_id IS NOT NULL;
CREATE UNIQUE INDEX pokemon_ability_current_unique ON pokemon_ability (pokemon_id, slot)
    WHERE generation_id IS NULL;

CREATE TABLE pokemon_moves
(
    id                INTEGER PRIMARY KEY,
    pokemon_id        INTEGER REFERENCES pokemon (id),
    move_id           INTEGER REFERENCES moves (id),
    move_learn_method INTEGER REFERENCES move_learn_methods (id),
    version_group_id  INTEGER REFERENCES version_groups (id),
    level_learned_at  INTEGER,
    "order"           INTEGER
);

CREATE UNIQUE INDEX pokemon_moves_unique ON pokemon_moves (pokemon_id, move_id, version_group_id);

CREATE TABLE pokemon_stats
(
    id            INTEGER PRIMARY KEY,
    pokemon_id    INTEGER REFERENCES pokemon (id),
    stat_id       INTEGER REFERENCES stats (id),
    generation_id INTEGER REFERENCES generations (id),
    base_stat     INTEGER,
    effort        INTEGER
);

CREATE UNIQUE INDEX pokemon_stats_generation_unique ON pokemon_stats (pokemon_id, stat_id, generation_id)
    WHERE generation_id IS NOT NULL;
CREATE UNIQUE INDEX pokemon_stats_current_unique ON pokemon_stats (pokemon_id, stat_id)
    WHERE generation_id IS NULL;

-- pokemon_types final schema (surrogate id so generation_id can be nullable)
CREATE TABLE pokemon_types
(
    id            INTEGER PRIMARY KEY,
    pokemon_id    INTEGER REFERENCES pokemon (id),
    slot          INTEGER,
    type_id       INTEGER REFERENCES types (id),
    generation_id INTEGER REFERENCES generations (id)
);

CREATE UNIQUE INDEX pokemon_types_generation_unique ON pokemon_types (pokemon_id, slot, generation_id)
    WHERE generation_id IS NOT NULL;
CREATE UNIQUE INDEX pokemon_types_current_unique ON pokemon_types (pokemon_id, slot)
    WHERE generation_id IS NULL;

-- pokedex_pokemon final schema (by version_group and pokemon instead of species)
CREATE TABLE pokedex_pokemon
(
    pokedex_id        INTEGER REFERENCES pokedexes (id),
    version_group_id  INTEGER NOT NULL REFERENCES version_groups (id),
    pokemon_id        INTEGER NOT NULL REFERENCES pokemon (id),
    entry_number      INTEGER,
    PRIMARY KEY (pokedex_id, version_group_id, pokemon_id)
);

