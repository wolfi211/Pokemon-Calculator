import { apiGet } from '@/api/client'
import type { MinifiedMoveSearchResponse } from '@/types/api/moveSummary'

export function searchMoves(
  query?: string,
  pokemonId?: number,
): Promise<MinifiedMoveSearchResponse[]> {
  return apiGet<MinifiedMoveSearchResponse[]>('/api/v1/moves/minified-search', {
    query,
    pokemonId,
  })
}
