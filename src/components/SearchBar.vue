<script setup lang="ts">
import { ref, reactive } from 'vue'
import PokemonSearch from './PokemonSearch.vue'
import MoveSearch from './MoveSearch.vue'
import { PokemonCalculatorService } from '@/services/PokemonCalculatorService'

// State to hold the final selection
const enemyPokemon = ref<any>(null)
const enemyMoves = reactive<Record<number, any>>({
    1: null, 2: null, 3: null, 4: null
})

const calculator = new PokemonCalculatorService()
const results = ref<any[]>([])
const isSearching = ref(false)

const emit = defineEmits(['results', 'loading'])

// Handlers for the emits
const handlePokemonUpdate = (pokemon: any) => {
    enemyPokemon.value = pokemon
}

const handleMoveUpdate = (data: { order: number, move: any }) => {
    enemyMoves[data.order] = data.move
}

const startSearch = async () => {
    if (!enemyPokemon.value) return

    isSearching.value = true
    emit('loading', true)

    try {
        const moveIds = Object.values(enemyMoves)
            .filter(m => m !== null)
            .map(m => m.id)

        const results = await calculator.findBestCounters({
            enemyPokemonId: enemyPokemon.value.id,
            enemyMoveIds: moveIds
        })

        emit('results', results)
    } finally {
        isSearching.value = false
        emit('loading', false)
    }
}
</script>

<template>
    <div class="mt-5 mx-auto w-fit flex flex-col items-center gap-6">
        <div class="flex justify-center content-center items-center gap-3">
            <div>
                <PokemonSearch @update:pokemon="handlePokemonUpdate" />
            </div>
            <div class="grid grid-cols-2 gap-3">
                <MoveSearch v-for="i in [1, 2, 3, 4]" :key="i" :order="i" @update:move="handleMoveUpdate" />
            </div>
        </div>

        <button @click="startSearch" :disabled="!enemyPokemon || isSearching" class="inline-flex items-center px-8 py-3 bg-indigo-500 text-white rounded-lg font-bold shadow-lg 
             hover:bg-indigo-400 disabled:bg-indigo-200 disabled:cursor-not-allowed transition-all 
             active:scale-95 min-w-50 justify-center">
            <svg v-if="isSearching" class="animate-spin -ml-1 mr-3 h-5 w-5 text-white"
                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor"
                    d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z">
                </path>
            </svg>
            {{ isSearching ? 'Searching...' : 'Calculate Matchup' }}
        </button>
    </div>
</template>