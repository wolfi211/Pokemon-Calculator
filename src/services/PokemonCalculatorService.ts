import { supabase } from '@/services/supabase'

export const TYPE_CHART: Record<number, Record<number, number>> = {
  1: { 6: 0.5, 8: 0, 9: 0.5 }, // Normal
  2: { 1: 2, 3: 0.5, 4: 0.5, 6: 2, 7: 0.5, 9: 2, 14: 0.5, 15: 2, 17: 2, 18: 0.5, 8: 0 }, // Fighting
  3: { 2: 2, 6: 0.5, 7: 2, 9: 0.5, 12: 2, 13: 0.5 }, // Flying
  4: { 5: 0.5, 6: 0.5, 8: 0.5, 9: 0, 12: 2, 18: 2, 4: 0.5 }, // Poison
  5: { 4: 2, 6: 2, 9: 2, 10: 2, 13: 2, 3: 0, 7: 0.5, 12: 0.5 }, // Ground
  6: { 2: 0.5, 3: 2, 5: 0.5, 7: 2, 9: 0.5, 10: 2, 15: 2 }, // Rock
  7: { 2: 0.5, 3: 0.5, 4: 0.5, 8: 0.5, 9: 0.5, 10: 0.5, 12: 2, 14: 2, 17: 2, 18: 0.5 }, // Bug
  8: { 1: 0, 14: 2, 8: 2, 17: 0.5 }, // Ghost
  9: { 6: 2, 9: 0.5, 10: 0.5, 11: 0.5, 13: 0.5, 15: 2, 18: 2 }, // Steel
  10: { 6: 0.5, 7: 2, 9: 2, 10: 0.5, 11: 0.5, 12: 2, 15: 2, 16: 0.5 }, // Fire
  11: { 5: 2, 6: 2, 10: 2, 11: 0.5, 12: 0.5, 16: 0.5 }, // Water
  12: { 3: 0.5, 4: 0.5, 5: 2, 6: 2, 7: 0.5, 9: 0.5, 10: 0.5, 11: 2, 12: 0.5, 16: 0.5 }, // Grass
  13: { 3: 2, 5: 0, 11: 2, 12: 0.5, 13: 0.5, 16: 0.5 }, // Electric
  14: { 2: 2, 4: 2, 9: 0.5, 14: 0.5, 17: 0 }, // Psychic
  15: { 3: 2, 5: 2, 9: 0.5, 10: 0.5, 11: 0.5, 12: 2, 15: 0.5, 16: 2 }, // Ice
  16: { 9: 0.5, 16: 2, 18: 0 }, // Dragon
  17: { 2: 0.5, 8: 2, 14: 2, 17: 0.5, 18: 0.5 }, // Dark
  18: { 2: 2, 4: 0.5, 9: 0.5, 10: 0.5, 16: 2, 17: 2 }, // Fairy
}

export class PokemonCalculatorService {

  async findBestCounters(input: { enemyPokemonId: number, enemyMoveIds: number[] }) {
    const { data: enemy } = await supabase
      .from('pokemon')
      .select('*')
      .eq('id', input.enemyPokemonId)
      .single()

    const { data: moves } = await supabase
      .from('moves')
      .select('type_id, damage_class')
      .in('id', input.enemyMoveIds)
      .neq('damage_class', 'status')

    const moveTypeIds = moves?.map(m => m.type_id) || []

    const { data: candidates } = await supabase
      .from('pokemon')
      .select('*, moves(*)')

    if (!candidates) return []

    const results = candidates.map(candidate => {
      let weaknesses = 0
      let resistances = 0
      let immunities = 0
      let neutral = 0

      for (const mTypeId of moveTypeIds) {
        const eff = this.calculateEffectiveness(mTypeId, candidate.type_ids)
        if (eff > 1) weaknesses++
        else if (eff === 0) immunities++
        else if (eff < 1) resistances++
        else neutral++
      }

      if (weaknesses > 0) return null

      const bestStat = candidate.attack >= candidate.special_attack ? 'physical' : 'special'
      const hasCounterMove = candidate.moves.some((m: any) => {
        if (m.damage_class !== bestStat) return false
        const eff = this.calculateEffectiveness(m.type_id, enemy.type_ids)
        return eff > 1
      })

      if (!hasCounterMove) return null

      const tier = moveTypeIds.length - (immunities + resistances)

      return {
        ...candidate,
        tier,
        isStab: this.checkSTAB(candidate, enemy.type_ids)
      }
    }).filter(Boolean)

    return results.sort((a, b) => a!.tier - b!.tier)
  }

  private calculateEffectiveness(attackTypeId: number, defenderTypeIds: number | number[]): number {
    const defs = Array.isArray(defenderTypeIds) ? defenderTypeIds : [defenderTypeIds]
    return defs.reduce((acc, defId) => acc * (TYPE_CHART[attackTypeId]?.[defId] ?? 1), 1)
  }

  private checkSTAB(pokemon: any, enemyTypeIds: number[]): boolean {
    return pokemon.type_ids.some((tId: number) =>
      enemyTypeIds.some(eId => (TYPE_CHART[tId]?.[eId] ?? 1) > 1)
    )
  }

}