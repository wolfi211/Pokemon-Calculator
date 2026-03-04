<script setup lang="ts">
import { PokemonService } from '@/services/PokemonService';
import { ref } from 'vue';
import Multiselect from '@vueform/multiselect';

const pokemonService = new PokemonService();
const selectedPokemon = ref<number | null>(null);

const multiselectClasses = {
    container: 'multiselect !border-2 !border-rose-500 !rounded-md',
    containerActive: 'is-active !ring-3 !ring-rose-500/30 !border-rose-600',
    search: 'multiselect-search !focus:ring-0 !outline-none',
    dropdown: 'multiselect-dropdown !border-2 !border-rose-600 !rounded-b-md',
    optionSelected: 'is-selected !bg-rose-500 !text-white',
    optionPointed: 'is-pointed !bg-rose-100 !text-rose-900',
};

const fetchPokemons = async (query: string) => {
    if (!query) return [];
    return await pokemonService.queryPokemon(query);
};
</script>

<template>
    <div class="w-100 h-full overflow-visible">
        <Multiselect v-model="selectedPokemon" placeholder="Enemy Pokemon" :filter-results="false" :min-chars="2"
            :resolve-on-load="false" :delay="300" :searchable="true" :options="fetchPokemons" value-prop="id"
            label="name" track-by="name" class="pokemon-multiselect" :classes="multiselectClasses">
            <template #singlelabel="{ value }">
                <div class="multiselect-single-label flex items-center gap-2">
                    <img :src="value.spriteUrl" class="w-6 h-6 object-contain" />
                    <span class="capitalize font-medium">{{ value.name }}</span>
                </div>
            </template>
            <template #option="{ option }">
                <div class="flex items-center gap-2">
                    <img :src="option.spriteUrl" class="w-8 h-8 object-contain" />
                    <span class="capitalize">{{ option.name }}</span>
                </div>
            </template>
        </Multiselect>
    </div>
</template>

<style src="@vueform/multiselect/themes/default.css"></style>