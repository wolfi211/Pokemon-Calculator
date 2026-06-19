<script setup lang="ts">
import { PokemonService } from '@/services/PokemonService'
import type { PokemonSummaryDto } from '@/types/api/pokemonSummary'
import { typeIdsFromSummary } from '@/utils/pokemonTypes'
import { computed, ref, watch } from 'vue'
import Multiselect from '@vueform/multiselect'
import { getTypeHex, getTypeTheme } from '@/constants/TypeColors'
import TypeIcon from './TypeIcon.vue'

const pokemonService = new PokemonService()
const selectedPokemon = ref<PokemonSummaryDto | null>(null)

const selectedTypeIds = computed(() =>
  selectedPokemon.value ? typeIdsFromSummary(selectedPokemon.value) : [],
)

const activeTheme = computed(() => {
  const firstTypeId = selectedTypeIds.value[0]
  return firstTypeId ? getTypeTheme(firstTypeId) : getTypeTheme(undefined)
})

const pokemonBorderStyle = computed(() => {
  let color1, color2

  if (!selectedTypeIds.value.length) {
    color1 = color2 = '#9FA19F'
  } else {
    const type1Id = selectedTypeIds.value[0]!
    const type2Id = selectedTypeIds.value[1] ?? type1Id
    color1 = getTypeHex(type1Id)
    color2 = getTypeHex(type2Id)
  }

  return {
    border: '2px solid transparent',
    'background-image': `linear-gradient(white, white), linear-gradient(135deg, ${color1} 50%, ${color2} 50%)`,
    'background-origin': 'border-box',
    'background-clip': 'padding-box, border-box',
    'border-radius': '6px',
  }
})

const moveIconUrls = computed(() =>
  selectedTypeIds.value.map((typeId) => ({
    url: new URL(`/src/assets/images/types/${typeId}.png`, import.meta.url).href,
    theme: getTypeTheme(typeId),
  })),
)

const multiselectClasses = computed(() => ({
  container: 'multiselect !rounded-md transition-all duration-300',
  containerActive: `is-active !ring-3 ${activeTheme.value.ring} ${activeTheme.value.border}`,
}))

const fetchPokemons = async (query: string) => {
  if (!query) return []
  return await pokemonService.queryPokemon(query)
}

const emit = defineEmits<{ 'update:pokemon': [pokemon: PokemonSummaryDto | null] }>()

watch(selectedPokemon, (newVal) => {
  emit('update:pokemon', newVal)
})
</script>

<template>
  <div class="w-100 h-full overflow-visible">
    <Multiselect
      v-model="selectedPokemon"
      placeholder="Enemy Pokemon"
      :filter-results="false"
      :min-chars="2"
      :resolve-on-load="false"
      :delay="300"
      :searchable="true"
      :options="fetchPokemons"
      value-prop="id"
      label="localizedName"
      track-by="name"
      class="pokemon-multiselect"
      :classes="multiselectClasses"
      :object="true"
      :key="selectedPokemon?.id"
      :style="pokemonBorderStyle"
    >
      <template #singlelabel="{ value }">
        <div class="multiselect-single-label flex items-center gap-2">
          <img v-if="value.sprite" :src="value.sprite" class="w-6 h-6 object-contain" />
          <span class="capitalize font-medium">{{ value.localizedName }}</span>
          <TypeIcon v-for="(icon, index) in moveIconUrls" :key="index" :url="icon.url" :type-theme="icon.theme" />
        </div>
      </template>
      <template #option="{ option }">
        <div class="flex items-center gap-2">
          <img v-if="option.sprite" :src="option.sprite" class="w-8 h-8 object-contain" />
          <span class="capitalize">{{ option.localizedName }}</span>
        </div>
      </template>
    </Multiselect>
  </div>
</template>

<style src="@vueform/multiselect/themes/default.css"></style>
