CREATE TABLE types (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE pokemon (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  species_name TEXT,
  hp INTEGER,
  attack INTEGER,
  defense INTEGER,
  special_attack INTEGER,
  special_defense INTEGER,
  speed INTEGER,
  sprite_url TEXT,
  type_ids INTEGER[]
);

CREATE TABLE moves (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  type_id INTEGER REFERENCES types(id)
);