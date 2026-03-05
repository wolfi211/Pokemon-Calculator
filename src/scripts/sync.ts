import { createClient } from '@supabase/supabase-js'
import dotenv from 'dotenv'

dotenv.config()

const supabase = createClient(
  process.env.VITE_SUPABASE_URL!,
  process.env.VITE_SUPABASE_ANON_KEY!
)

const baseUrl = 'https://pokeapi.co/api/v2';

async function syncPokedex() {
  console.log("🚀 Starting Sync...")

  console.log("🚀 Starting Type Sync...")
  const typeRes = await fetch(`${baseUrl}/type?limit=100`)
  const { results: types } = await typeRes.json()

  for (const type of types) {
    const detail = await (await fetch(type.url)).json()
    await supabase.from('types').upsert({ id: detail.id, name: detail.name })
    console.log(`Syncing Type: ${detail.id} - ${detail.name}`)
  }
  console.log("✅ Types Synced")

  console.log("🚀 Starting Move Sync...")
  const moveRes = await fetch(`${baseUrl}/move?limit=1000`)
  const { results: moves } = await moveRes.json()

  const moveData = []
  for (const move of moves) {
    const d = await (await fetch(move.url)).json()
    moveData.push({
      id: d.id,
      name: d.name,
      type_id: parseInt(d.type.url.split('/').filter(Boolean).pop()),
      power: d.power,
      damage_class: d.damage_class?.name
    })
    console.log(`Syncing Move: ${d.id} - ${d.name}`)
    if (moveData.length >= 100) {
      await supabase.from('moves').upsert(moveData)
      moveData.length = 0
    }
  }

  if (moveData.length > 0) {
    await supabase.from('moves').upsert(moveData)
  }
  console.log("✅ Moves Synced")

  const directoryRes = await fetch(`${baseUrl}/pokemon?limit=2000`)
  const { results: allPokemonRefs } = await directoryRes.json()
  console.log(`Found ${allPokemonRefs.length} entries. Starting fetch...`)

  const pokemonBatch: any[] = []
  const moveLinkBatch: any[] = []

  for (const ref of allPokemonRefs) {
    const id = parseInt(ref.url.split('/').filter(Boolean).pop()!)

    try {
      const d = await (await fetch(ref.url)).json()

      const pokemonRecord = {
        id: d.id,
        name: d.name,
        species_name: d.species.name,
        hp: d.stats[0].base_stat,
        attack: d.stats[1].base_stat,
        defense: d.stats[2].base_stat,
        special_attack: d.stats[3].base_stat,
        special_defense: d.stats[4].base_stat,
        speed: d.stats[5].base_stat,
        sprite_url: d.sprites.front_default,
        type_ids: d.types.map((t: any) => parseInt(t.type.url.split('/').filter(Boolean).pop()!))
      }

      pokemonBatch.push(pokemonRecord)

      const moveLinks = d.moves.map((m: any) => ({
        pokemon_id: d.id,
        move_id: parseInt(m.move.url.split('/').filter(Boolean).pop()!)
      }))

      moveLinkBatch.push(...moveLinks)

      console.log(`Syncing Pokemon: ${d.id} - ${d.name}`)

      if (pokemonBatch.length >= 50) {
        await supabase.from('pokemon').upsert(pokemonBatch)
        await supabase.from('pokemon_moves').upsert(moveLinkBatch)
        console.log(`✅ Synced batch up to: ${d.name}`)
        pokemonBatch.length = 0
        moveLinkBatch.length = 0
      }
    } catch (err) {
      console.error(`❌ Failed to sync ID from ${ref.url}:`, err)
    }
  }

  if (pokemonBatch.length > 0) {
    await supabase.from('pokemon').upsert(pokemonBatch)
    await supabase.from('pokemon_moves').upsert(moveLinkBatch)
  }

  console.log("🎉 All Done!")
}

syncPokedex()