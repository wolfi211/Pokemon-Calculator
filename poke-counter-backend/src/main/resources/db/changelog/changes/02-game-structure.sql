-- liquibase formatted sql

--changeset daniel.wolf:2 context:game_structure
--comment: Setting up versions and pokedexes
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

CREATE TABLE pokedex_version_group
(
    id               INTEGER PRIMARY KEY,
    pokedex_id       INTEGER REFERENCES pokedexes (id),
    version_group_id INTEGER REFERENCES version_groups (id)
);

CREATE TABLE learn_method_version_group
(
    id               INTEGER PRIMARY KEY,
    learn_method_id  INTEGER REFERENCES move_learn_methods (id),
    version_group_id INTEGER REFERENCES version_groups (id)
);