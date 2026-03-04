<script setup lang="ts">
import { PokemonService } from '@/services/PokemonService';
import type { Pokemon } from '@/types/pokemon';
import { ref } from 'vue';


//https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png

const pokemonService = new PokemonService()

const pokemons: Pokemon[] = await pokemonService.getAllPokemon()

const selectedPokemon = ref<Pokemon>()
const isLoading = ref<Boolean>()
</script>

<template>
    <div class="h-fit border border-rose-500 p-3 rounded-md">
        Pokemon Search
    </div>
</template>