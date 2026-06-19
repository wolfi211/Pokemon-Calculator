import type { PokemonSummaryDto } from './pokemonSummary'

export interface CounterResultDto {
  pokemon: PokemonSummaryDto
  tier: number
  hasStab: boolean
}

export interface CounterFindResponse {
  results: CounterResultDto[]
}

export interface CounterFindRequest {
  enemyPokemonId: number
  enemyMoveIds: number[]
}
