import type { Move } from "./move"
import type { StatBlock } from "./statBlock"
import type { Type } from "./type"

export interface Pokemon {
  id: number
  name: string
  types: number[]
  stats: StatBlock
  moves: Move[]
  spriteUrl: string
  speciesName: string
}

export const toPokemon = (row: any): Pokemon => ({
  id: row.id,
  name: row.name,
  spriteUrl: row.sprite_url,
  stats: {
    hp: row.hp,
    attack: row.attack,
    defense: row.defense,
    specialAttack: row.special_attack,
    specialDefense: row.special_defense,
    speed: row.speed
  } as StatBlock,
  types: row.type_ids,
  moves: [],
  speciesName: row.species_name
})