<script setup lang="ts">
import { ref, reactive, watch } from 'vue'
import PokemonSearch from './PokemonSearch.vue'
import MoveSearch from './MoveSearch.vue'
import { PokemonCalculatorService } from '@/services/PokemonCalculatorService'
import PokemonDisplay from './PokemonDisplay.vue'
import type { PokemonSummaryDto } from '@/types/api/pokemonSummary'
import type { MinifiedMoveSearchResponse } from '@/types/api/moveSummary'
import type { CounterResultDto } from '@/types/api/counterResult'

const enemyPokemon = ref<PokemonSummaryDto | null>(null)
const enemyMoves = reactive<Record<number, MinifiedMoveSearchResponse | null>>({
  1: null,
  2: null,
  3: null,
  4: null,
})

const calculator = new PokemonCalculatorService()
const isSearching = ref(false)

const emit = defineEmits<{
  results: [results: CounterResultDto[]]
  loading: [loading: boolean]
}>()

const handlePokemonUpdate = (pokemon: PokemonSummaryDto | null) => {
  enemyPokemon.value = pokemon
}

const handleMoveUpdate = (data: { order: number; move: MinifiedMoveSearchResponse | null }) => {
  enemyMoves[data.order] = data.move
}

watch(enemyPokemon, () => {
  for (const key of [1, 2, 3, 4] as const) {
    enemyMoves[key] = null
  }
})

const startSearch = async () => {
  if (!enemyPokemon.value) return

  isSearching.value = true
  emit('loading', true)

  try {
    const moveIds = Object.values(enemyMoves)
      .filter((m): m is MinifiedMoveSearchResponse => m !== null)
      .map((m) => m.id)

    const results = await calculator.findBestCounters({
      enemyPokemonId: enemyPokemon.value.id,
      enemyMoveIds: moveIds,
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
    <div class="flex justify-center content-start items-start gap-3">
      <div class="flex flex-col gap-3">
        <PokemonSearch @update:pokemon="handlePokemonUpdate" />
      </div>
      <div class="grid grid-cols-2 gap-3 h-full">
        <MoveSearch
          v-for="i in [1, 2, 3, 4]"
          :key="`${i}-${enemyPokemon?.id ?? 'none'}`"
          :order="i"
          :pokemon-id="enemyPokemon?.id"
          @update:move="handleMoveUpdate"
        />
      </div>
    </div>

    <PokemonDisplay v-if="enemyPokemon" :pokemon="enemyPokemon" class="w-full" />

    <button
      @click="startSearch"
      :disabled="!enemyPokemon || isSearching"
      class="inline-flex items-center px-8 py-3 bg-red-500 text-white rounded-lg font-bold shadow-lg hover:bg-red-400 disabled:bg-red-200 disabled:cursor-not-allowed transition-all active:scale-95 min-w-50 justify-center"
    >
      <svg
        v-if="isSearching"
        class="animate-spin -ml-1 mr-3 h-5 w-5 text-white"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
      >
        <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
        <path
          class="opacity-75"
          fill="currentColor"
          d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
        ></path>
      </svg>
      {{ isSearching ? 'Searching...' : 'Find Counter' }}
    </button>
  </div>
</template>
