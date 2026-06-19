import type { MinifiedTypeDto } from './minifiedType'
import type { StatBlockDto } from './statBlock'

export interface MinifiedSpeciesDto {
  name: string
}

export interface PokemonSummaryDto {
  id: number
  name: string
  sprite: string | null
  cry: string | null
  localizedName: string
  species: MinifiedSpeciesDto
  types: MinifiedTypeDto[]
  stats: StatBlockDto
}
