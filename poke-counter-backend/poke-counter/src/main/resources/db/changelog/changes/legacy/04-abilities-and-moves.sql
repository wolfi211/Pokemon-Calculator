--liquibase formatted sql

--changeset daniel.wolf:4 context:abilities_and_moves
--comment: Creating abilities and movesets
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

