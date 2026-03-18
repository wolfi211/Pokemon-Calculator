--liquibase formatted sql

--changeset daniel.wolf:baseline-03-moves context:baseline
--comment: Baseline - abilities, moves, and move types
CREATE TABLE ability
(
    id             INTEGER PRIMARY KEY,
    name           VARCHAR(255) NOT NULL UNIQUE,
    is_main_series BOOLEAN,
    generation     INTEGER,
    names          JSONB,
    flavor_texts   JSONB
);

CREATE TABLE moves
(
    id           INTEGER PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    priority     INTEGER,
    damage_class INTEGER REFERENCES damage_classes (id),
    flavor_texts JSONB,
    generation   INTEGER REFERENCES generations (id),
    names        JSONB
);

-- Sequence for sequence-backed move_types rows (JPA assigns ids; no defaults in SQL)
CREATE SEQUENCE move_types_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE move_types
(
    id               INTEGER PRIMARY KEY,
    move_id          INTEGER REFERENCES moves (id),
    accuracy         INTEGER,
    effect_chance    INTEGER,
    pp               INTEGER,
    power            INTEGER,
    effect_entries   JSONB,
    type_id          INTEGER REFERENCES types (id),
    version_group_id INTEGER REFERENCES version_groups (id)
);

-- One row per (move, version_group); allow exactly one \"current\" row where version_group_id is NULL
CREATE UNIQUE INDEX move_types_version_group_unique ON move_types (move_id, version_group_id)
    WHERE version_group_id IS NOT NULL;
CREATE UNIQUE INDEX move_types_current_unique ON move_types (move_id)
    WHERE version_group_id IS NULL;

