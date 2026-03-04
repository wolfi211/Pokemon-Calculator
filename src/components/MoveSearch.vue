<script setup lang="ts">
import { PokemonService } from '@/services/PokemonService';
import { ref } from 'vue';
import Multiselect from '@vueform/multiselect';

const pokemonService = new PokemonService();
const selectedMove = ref<number | null>(null);

const multiselectClasses = {
    container: 'multiselect !border-2 !border-emerald-500 !rounded-md',
    containerActive: 'is-active !ring-3 !ring-emerald-500/30 !border-emerald-600',
    search: 'multiselect-search !focus:ring-0 !outline-none',
    dropdown: 'multiselect-dropdown !border-2 !border-emerald-600 !rounded-b-md',
    optionSelected: 'is-selected !bg-emerald-500 !text-white',
    optionPointed: 'is-pointed !bg-emerald-100 !text-emerald-900',
};

const fetchMoves = async (query: string) => {
    if (!query) return [];
    return await pokemonService.queryMoves(query);
};
</script>

<template>
    <div class="w-75 h-full overflow-visible">
        <Multiselect v-model="selectedMove" placeholder="Move" :filter-results="false" :min-chars="2"
            :resolve-on-load="false" :delay="300" :searchable="true" :options="fetchMoves" value-prop="id" label="name"
            track-by="name" class="pokemon-multiselect" :classes="multiselectClasses">
            <template #singlelabel="{ value }">
                <div class="multiselect-single-label flex items-center gap-2">
                    <span class="capitalize font-medium">{{ value.name }}</span>
                </div>
            </template>
            <template #option="{ option }">
                <div class="flex items-center gap-2">
                    <span class="capitalize">{{ option.name }}</span>
                </div>
            </template>
        </Multiselect>
    </div>
</template>

<style src="@vueform/multiselect/themes/default.css"></style>