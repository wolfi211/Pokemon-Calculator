<script setup lang="ts">
import { ref } from 'vue'
import SearchBar from '@/components/SearchBar.vue'
import PokemonList from '@/components/PokemonList.vue'
import PokemonDisplay from '@/components/PokemonDisplay.vue'
import { PokemonService } from '@/services/PokemonService'

const counterResults = ref<any[]>([])
const service = new PokemonService()
const pokemon = ref<any>()

const handleSearchResults = (data: any[]) => {
    counterResults.value = data

    if (data.length > 0) {
        setTimeout(() => {
            document.getElementById('results-section')?.scrollIntoView({ behavior: 'smooth' })
        }, 100)
    }
}

</script>

<template>
    <main class="min-h-screen bg-slate-50 py-12 px-4">
        <div class="max-w-6xl mx-auto">
            <header class="text-center mb-12">
                <h1 class="text-4xl font-black text-slate-900 tracking-tight mb-2">
                    POKÉMON <span class="text-emerald-600">COUNTER</span> FINDER
                </h1>
                <p class="text-slate-500">Select an enemy and their moves to find the perfect walls.</p>
            </header>

            <section class="bg-white p-8 rounded-2xl shadow-sm border border-slate-100">
                <SearchBar @results="handleSearchResults" />
            </section>

            <section id="results-section" class="mt-12">
                <PokemonList v-if="counterResults.length > 0" :counters="counterResults" />

                <!-- <div v-else class="text-center py-20 border-2 border-dashed border-slate-200 rounded-2xl">
                    <p class="text-slate-400 font-medium">
                        No results yet. Perform a search to see recommendations.
                    </p>
                </div> -->
            </section>
        </div>
    </main>
</template>