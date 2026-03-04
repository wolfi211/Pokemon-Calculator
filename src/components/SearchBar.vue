<script setup lang="ts">
import MoveSearch from './MoveSearch.vue';
import PokemonSearch from './PokemonSearch.vue';


</script>

<template>
    <div class="mt-10 mx-auto w-fit flex justify-center content-start items-start gap-3">
        <div>
            <PokemonSearch />
        </div>
        <div class="grid grid-cols-2 gap-3">
            <MoveSearch v-for="i in [1,2,3,4]" :order="i" />
        </div>
    </div>
</template>