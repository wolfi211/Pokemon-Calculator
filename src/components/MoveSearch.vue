<script setup lang="ts">
import { PokemonService } from '@/services/PokemonService'
import { computed, ref, watch } from 'vue'
import Multiselect from '@vueform/multiselect'
import { getTypeTheme } from '@/constants/TypeColors'
import TypeIcon from './TypeIcon.vue'


const props = defineProps<{ order: number }>()
const pokemonService = new PokemonService()
const selectedMove = ref<any>(null)
const activeTheme = computed(() => {
    return getTypeTheme(selectedMove.value?.type)
})
const moveIconUrl = computed(() => {
    const typeId = selectedMove.value?.type
    if (!typeId) return ''
    return new URL(`/src/assets/images/types/${typeId}.png`, import.meta.url).href
})

const multiselectClasses = computed(() => ({
    container: `multiselect !border-2 !rounded-md ${activeTheme.value.border}`,
    containerActive: `is-active !ring-3 ${activeTheme.value.ring} ${activeTheme.value.border}`,
    search: 'multiselect-search !focus:ring-0 !outline-none'
}))

const fetchMoves = async (query: string) => {
    if (!query) return []
    return await pokemonService.queryMoves(query)
}


const emit = defineEmits(['update:move'])

watch(selectedMove, (newVal) => {
    emit('update:move', { order: props.order, move: newVal })
})
</script>

<template>
    <div class="w-75 h-full overflow-visible">
        <Multiselect v-model="selectedMove" placeholder="Move" :filter-results="false" :min-chars="2"
            :resolve-on-load="false" :delay="300" :searchable="true" :options="fetchMoves" value-prop="id" label="name"
            track-by="name" class="pokemon-multiselect" :classes="multiselectClasses" :object="true"
            :key="selectedMove?.type_id ?? 'none'">
            <template #singlelabel="{ value }">
                <div class="multiselect-single-label flex items-center gap-2">
                    <TypeIcon :url="moveIconUrl" :type-theme="activeTheme" />
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