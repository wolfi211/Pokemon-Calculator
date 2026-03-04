import { supabase } from '@/services/supabase';
import { type Move, toMove } from '@/types/move';
import { toPokemon, type Pokemon } from '@/types/pokemon';

export class PokemonService {

    async getAllPokemon() {
        const { data: pokemons } = await supabase
      .from('pokemon')
      .select('*')

      return pokemons as Pokemon[]
    }

    async queryPokemon(query: string): Promise<Pokemon[]> {
        const {data, error } = await supabase
            .from('pokemon')
            .select('*')
            .ilike("name", `%${query}%`)

        if(error) {
            console.error(error)
            return []
        }

        return data.map(toPokemon)
    }

    async queryMoves(query: string): Promise<Move[]> {
        const {data, error } = await supabase
            .from('moves')
            .select('*')
            .ilike("name", `%${query}%`)

        if(error) {
            console.error(error)
            return []
        }

        return data.map(toMove)
    }
}