<script setup lang="ts">
import { ref } from 'vue'
import SearchBar from '@/components/SearchBar.vue'
import PokemonList from '@/components/PokemonList.vue'
import GithubLink from '@/components/GithubLink.vue'

const counterResults = ref<any[]>([])

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
    <main class="min-h-screen bg-neutral-800 py-12 px-4">
        <div class="max-w-6xl mx-auto">
            <header class="text-center mb-12">
                <h1 class="text-4xl font-black text-neutral-100 tracking-tight mb-2">
                    POKÉMON <span class="text-red-500">COUNTER</span> FINDER
                </h1>
                <p class="text-neutral-100/50">Select an enemy and their moves to find the perfect counter.</p>
            </header>

            <section class="bg-white p-8 rounded-2xl shadow-sm">
                <SearchBar @results="handleSearchResults" />
            </section>

            <section id="results-section" class="mt-12">
                <PokemonList v-if="counterResults.length > 0" :counters="counterResults" />
            </section>
        </div>
        <GithubLink />
    </main>
</template>