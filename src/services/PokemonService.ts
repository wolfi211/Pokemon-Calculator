import { supabase } from '@/services/supabase';
import type { Pokemon } from '@/types/pokemon';

export class PokemonService {

    async getAllPokemon() {
        const { data: pokemons } = await supabase
      .from('pokemon')
      .select('*')

      return pokemons as Pokemon[]
    }

    async queryPokemon(search: string): Promise<Pokemon[]> {
        const {data: pokemons } = await supabase
            .from('pokemon')
            .select('*')
            .like("name", search)
            
    }
}