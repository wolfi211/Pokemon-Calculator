import { apiPost } from '@/api/client'
import type { CounterFindRequest, CounterFindResponse } from '@/types/api/counterResult'

export function findCounters(
  enemyPokemonId: number,
  enemyMoveIds: number[],
): Promise<CounterFindResponse> {
  const body: CounterFindRequest = { enemyPokemonId, enemyMoveIds }
  return apiPost<CounterFindResponse, CounterFindRequest>('/api/v1/counters/find', body)
}
