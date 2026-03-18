--liquibase formatted sql

--changeset yourname:6 context:sequences
--comment: Creating sequences for tables that don't have PokéAPI IDs

-- Sequences
CREATE SEQUENCE type_relations_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokemon_ability_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokemon_moves_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokemon_stats_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE move_types_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pokedex_version_group_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE learn_method_version_group_id_seq START WITH 1 INCREMENT BY 1;

-- Applying sequences to the tables
ALTER TABLE type_relations ALTER COLUMN id SET DEFAULT nextval('type_relations_id_seq');
ALTER TABLE pokemon_ability ALTER COLUMN id SET DEFAULT nextval('pokemon_ability_id_seq');
ALTER TABLE pokemon_moves ALTER COLUMN id SET DEFAULT nextval('pokemon_moves_id_seq');
ALTER TABLE pokemon_stats ALTER COLUMN id SET DEFAULT nextval('pokemon_stats_id_seq');
ALTER TABLE move_types ALTER COLUMN id SET DEFAULT nextval('move_types_id_seq');
ALTER TABLE pokedex_version_group ALTER COLUMN id SET DEFAULT nextval('pokedex_version_group_id_seq');
ALTER TABLE learn_method_version_group ALTER COLUMN id SET DEFAULT nextval('learn_method_version_group_id_seq');

