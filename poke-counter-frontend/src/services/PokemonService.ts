import { searchMoves as apiSearchMoves } from '@/api/moveApi'
import { searchPokemon as apiSearchPokemon } from '@/api/pokemonApi'
import type { MinifiedMoveSearchResponse } from '@/types/api/moveSummary'
import type { PokemonSummaryDto } from '@/types/api/pokemonSummary'

export class PokemonService {
  async queryPokemon(query: string): Promise<PokemonSummaryDto[]> {
    if (!query) return []
    return apiSearchPokemon(query)
  }

  async queryMoves(query: string, pokemonId?: number): Promise<MinifiedMoveSearchResponse[]> {
    if (!query) return []
    return apiSearchMoves(query, pokemonId)
  }
}
