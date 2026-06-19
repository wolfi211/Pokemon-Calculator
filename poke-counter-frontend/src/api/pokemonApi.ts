import { apiGet } from '@/api/client'
import type { PokemonSummaryDto } from '@/types/api/pokemonSummary'

export function searchPokemon(
  query?: string,
  versionGroup?: number,
): Promise<PokemonSummaryDto[]> {
  return apiGet<PokemonSummaryDto[]>('/api/v1/pokemon/minified-search', {
    query,
    versionGroup,
  })
}
