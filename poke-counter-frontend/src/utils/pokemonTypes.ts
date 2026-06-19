import type { MinifiedTypeDto } from '@/types/api/minifiedType'
import type { PokemonSummaryDto } from '@/types/api/pokemonSummary'

export function typeIdsFromTypes(types: MinifiedTypeDto[]): number[] {
  return [...types]
    .sort((a, b) => (a.slot ?? 0) - (b.slot ?? 0))
    .map((t) => t.id)
}

export function typeIdsFromSummary(pokemon: PokemonSummaryDto): number[] {
  return typeIdsFromTypes(pokemon.types)
}

export function primaryTypeId(types: MinifiedTypeDto[]): number | undefined {
  return typeIdsFromTypes(types)[0]
}
