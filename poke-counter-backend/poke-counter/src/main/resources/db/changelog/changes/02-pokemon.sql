--liquibase formatted sql

--changeset daniel.wolf:baseline-02-pokemon context:baseline
--comment: Baseline - species, pokemon, and forms
CREATE TABLE species
(
    id                     INTEGER PRIMARY KEY,
    name                   VARCHAR(255) NOT NULL UNIQUE,
    "order"                INTEGER,
    gender_rate            INTEGER,
    capture_rate           INTEGER,
    base_happiness         INTEGER,
    is_baby                BOOLEAN,
    is_legendary           BOOLEAN,
    is_mythical            BOOLEAN,
    hatch_counter          INTEGER,
    has_gender_differences BOOLEAN,
    forms_switchable       BOOLEAN,
    names                  JSONB,
    flavor_text_entries    JSONB,
    form_descriptions      JSONB
);

CREATE TABLE pokemon
(
    id              INTEGER PRIMARY KEY,
    name            VARCHAR(255) NOT NULL UNIQUE,
    base_experience INTEGER,
    height          INTEGER,
    is_default      BOOLEAN,
    "order"         INTEGER,
    weight          INTEGER,
    sprite          TEXT,
    cry             TEXT,
    species_id      INTEGER REFERENCES species (id)
);

CREATE TABLE pokemon_forms
(
    id             INTEGER PRIMARY KEY,
    name           VARCHAR(255) NOT NULL UNIQUE,
    "order"        INTEGER,
    form_order     INTEGER,
    is_default     BOOLEAN,
    is_battle_only BOOLEAN,
    is_mega        BOOLEAN,
    form_name      VARCHAR(255) NOT NULL,
    pokemon_id     INTEGER REFERENCES pokemon (id),
    sprite         TEXT,
    version_group  INTEGER REFERENCES version_groups (id),
    names          JSONB,
    form_names     JSONB
);

