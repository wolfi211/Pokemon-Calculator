--liquibase formatted sql

--changeset daniel.wolf:1 context:foundation
--comment: Initial setup of base lookup tables
CREATE TABLE generations
(
    id    INTEGER PRIMARY KEY,
    name  VARCHAR(255) NOT NULL UNIQUE,
    names JSONB
);

CREATE TABLE regions
(
    id              INTEGER PRIMARY KEY,
    name            VARCHAR(255) NOT NULL UNIQUE,
    names           JSONB,
    main_generation INTEGER REFERENCES generations (id)
);

CREATE TABLE types
(
    id    INTEGER PRIMARY KEY,
    name  VARCHAR(255) NOT NULL UNIQUE,
    names JSONB
);

CREATE TABLE stats
(
    id    INTEGER PRIMARY KEY,
    name  VARCHAR(255) NOT NULL UNIQUE,
    names JSONB
);

CREATE TABLE damage_classes
(
    id           INTEGER PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    names        JSONB,
    descriptions JSONB
);

CREATE TABLE move_learn_methods
(
    id           INTEGER PRIMARY KEY,
    name         VARCHAR(255) NOT NULL UNIQUE,
    descriptions JSONB,
    names        JSONB
);

