import { findCounters } from '@/api/counterApi'
import type { CounterResultDto } from '@/types/api/counterResult'

export class PokemonCalculatorService {
  async findBestCounters(input: {
    enemyPokemonId: number
    enemyMoveIds: number[]
  }): Promise<CounterResultDto[]> {
    const response = await findCounters(input.enemyPokemonId, input.enemyMoveIds)
    return response.results
  }
}
