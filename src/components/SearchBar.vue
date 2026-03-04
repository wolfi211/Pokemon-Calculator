<script setup lang="ts">
import MoveSearch from './MoveSearch.vue';
import PokemonSearch from './PokemonSearch.vue';


</script>

<template>
    <div class="mx-auto w-fit flex justify-center content-center items-center gap-3">
        <PokemonSearch />
        <div class="grid grid-cols-2 gap-3">
            <MoveSearch v-for="i in [1,2,3,4]" :order="i" />
        </div>
    </div>
</template>