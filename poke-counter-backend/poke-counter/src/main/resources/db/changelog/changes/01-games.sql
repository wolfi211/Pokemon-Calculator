--liquibase formatted sql

--changeset daniel.wolf:baseline-01-games context:baseline
--comment: Baseline - games/versioning and pokedex structure
CREATE TABLE version_groups
(
    id            INTEGER PRIMARY KEY,
    name          VARCHAR(255) NOT NULL UNIQUE,
    "order"       INTEGER,
    generation_id INTEGER REFERENCES generations (id)
);

CREATE TABLE version
(
    id               INTEGER PRIMARY KEY,
    name             VARCHAR(255) NOT NULL UNIQUE,
    names            JSONB,
    version_group_id INTEGER REFERENCES version_groups (id)
);

CREATE TABLE pokedexes
(
    id             INTEGER PRIMARY KEY,
    name           VARCHAR(255) NOT NULL UNIQUE,
    is_main_series BOOLEAN,
    descriptions   JSONB,
    names          JSONB,
    region         INTEGER REFERENCES regions (id)
);

-- Sequences for sequence-backed junction tables in this domain (JPA assigns ids; no defaults in SQL)
CREATE SEQUENCE pokedex_version_group_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE learn_method_version_group_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE pokedex_version_group
(
    id               INTEGER PRIMARY KEY,
    pokedex_id       INTEGER REFERENCES pokedexes (id),
    version_group_id INTEGER REFERENCES version_groups (id)
);

CREATE UNIQUE INDEX pokedex_version_group_unique ON pokedex_version_group (pokedex_id, version_group_id);

CREATE TABLE learn_method_version_group
(
    id               INTEGER PRIMARY KEY,
    learn_method_id  INTEGER REFERENCES move_learn_methods (id),
    version_group_id INTEGER REFERENCES version_groups (id)
);

CREATE UNIQUE INDEX learn_method_version_group_unique ON learn_method_version_group (learn_method_id, version_group_id);

