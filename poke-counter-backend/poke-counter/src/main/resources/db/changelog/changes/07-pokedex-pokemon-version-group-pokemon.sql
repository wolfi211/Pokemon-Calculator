--liquibase formatted sql

--changeset daniel.wolf:7 context:pokedex_pokemon
--comment: PokedexPokemon by version_group and pokemon (variety) instead of species
ALTER TABLE pokedex_pokemon
    DROP CONSTRAINT pokedex_pokemon_pkey;

ALTER TABLE pokedex_pokemon
    DROP COLUMN species_id;

ALTER TABLE pokedex_pokemon
    ADD COLUMN version_group_id INTEGER NOT NULL REFERENCES version_groups (id);

ALTER TABLE pokedex_pokemon
    ADD COLUMN pokemon_id INTEGER NOT NULL REFERENCES pokemon (id);

ALTER TABLE pokedex_pokemon
    ADD CONSTRAINT pokedex_pokemon_pkey PRIMARY KEY (pokedex_id, version_group_id, pokemon_id);
