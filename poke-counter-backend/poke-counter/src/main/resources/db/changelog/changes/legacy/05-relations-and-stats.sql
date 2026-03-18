--liquibase formatted sql

--changeset daniel.wolf:5 context:relations_and_stats
--comment: Junction tables and complex relationships
CREATE TABLE type_relations
(
    id          INTEGER PRIMARY KEY,
    damage_from INTEGER REFERENCES types (id),
    damage_to   INTEGER REFERENCES types (id),
    multiplier  DECIMAL(3, 1),
    generation  INTEGER REFERENCES generations (id)
);

CREATE TABLE pokemon_ability
(
    id            INTEGER PRIMARY KEY,
    pokemon_id    INTEGER REFERENCES pokemon (id),
    ability_id    INTEGER REFERENCES ability (id),
    is_hidden     BOOLEAN,
    slot          INTEGER,
    generation_id INTEGER REFERENCES generations (id)
);

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

CREATE TABLE pokemon_types
(
    pokemon_id    INTEGER REFERENCES pokemon (id),
    slot          INTEGER,
    type_id       INTEGER REFERENCES types (id),
    generation_id INTEGER REFERENCES generations (id),
    PRIMARY KEY (pokemon_id, slot, generation_id)
);

CREATE TABLE pokemon_stats
(
    id            INTEGER PRIMARY KEY,
    pokemon_id    INTEGER REFERENCES pokemon (id),
    stat_id       INTEGER REFERENCES stats (id),
    generation_id INTEGER REFERENCES generations (id),
    base_stat     INTEGER,
    effort        INTEGER
);

CREATE TABLE pokedex_pokemon
(
    pokedex_id   INTEGER REFERENCES pokedexes (id),
    species_id   INTEGER REFERENCES species (id),
    entry_number INTEGER,
    PRIMARY KEY (pokedex_id, species_id)
);

