-- 1. Types table
CREATE TABLE types (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL
);

-- 2. Pokemon table
CREATE TABLE pokemon (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  hp INTEGER,
  attack INTEGER,
  defense INTEGER,
  special_attack INTEGER,
  special_defense INTEGER,
  speed INTEGER,
  sprite_url TEXT,
  type_ids INTEGER[] -- Array of Type IDs
);

-- 3. Moves table
CREATE TABLE moves (
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  type_id INTEGER REFERENCES types(id)
);