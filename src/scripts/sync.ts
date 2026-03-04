import { createClient } from '@supabase/supabase-js';
import dotenv from 'dotenv';

dotenv.config();

const supabase = createClient(
  process.env.VITE_SUPABASE_URL!,
  process.env.VITE_SUPABASE_ANON_KEY!
);

const baseUrl = process.env.POKEAPI_BASE_URL

async function syncPokedex() {
  console.log("🚀 Starting Sync...");

  // 1. Sync Types
  const typeRes = await fetch(`${baseUrl}/type?limit=100`);
  const { results: types } = await typeRes.json();

  for (const type of types) {
    const detail = await (await fetch(type.url)).json();
    await supabase.from('types').upsert({ id: detail.id, name: detail.name });
  }
  console.log("✅ Types Synced");

  // 2. Sync Moves (This takes a moment)
  const moveRes = await fetch(`${baseUrl}/move?limit=1000`);
  const { results: moves } = await moveRes.json();

  const moveData = [];
  for (const move of moves) {
    const d = await (await fetch(move.url)).json();
    moveData.push({
      id: d.id,
      name: d.name,
      type_id: parseInt(d.type.url.split('/').filter(Boolean).pop()),
      power: d.power,
      damage_class: d.damage_class?.name
    });
    // Upload in batches of 100 to avoid timeouts
    if (moveData.length >= 100) {
      await supabase.from('moves').upsert(moveData);
      moveData.length = 0;
    }
  }
  console.log("✅ Moves Synced");

  const POKEMON_LIMIT = 151;
  for (let i = 1; i <= POKEMON_LIMIT; i++) {
    const d = await (await fetch(`${baseUrl}/pokemon/${i}`)).json();

    const pokemonRecord = {
      id: d.id,
      name: d.name,
      hp: d.stats[0].base_stat,
      attack: d.stats[1].base_stat,
      defense: d.stats[2].base_stat,
      special_attack: d.stats[3].base_stat,
      special_defense: d.stats[4].base_stat,
      speed: d.stats[5].base_stat,
      sprite_url: d.sprites.front_default,
      type_ids: d.types.map((t: any) => parseInt(t.type.url.split('/').filter(Boolean).pop()))
    };

    await supabase.from('pokemon').upsert(pokemonRecord);

    // Link Moves in junction table
    const moveLinks = d.moves.map((m: any) => ({
      pokemon_id: d.id,
      move_id: parseInt(m.move.url.split('/').filter(Boolean).pop())
    }));
    await supabase.from('pokemon_moves').upsert(moveLinks);

    console.log(`Synced: ${d.name}`);
  }

  console.log("🎉 All Done!");
}

syncPokedex();