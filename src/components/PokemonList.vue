<script setup lang="ts">
import { computed, ref } from 'vue'
import PokemonDisplay from './PokemonDisplay.vue'

const props = defineProps<{
    counters: any[]
}>()

const collapsedTiers = ref<Record<string, boolean>>({})
const sortBy = ref<'hp' | 'attack' | 'special_attack' | 'speed'>('speed')

const toggleTier = (tier: string) => {
    collapsedTiers.value[tier] = !collapsedTiers.value[tier]
}

const groupedResults = computed(() => {
    const groups: Record<number, any[]> = {}

    props.counters.forEach(pokemon => {
        const tier = pokemon.tier ?? 0
        if (!groups[tier]) groups[tier] = []
        groups[tier].push(pokemon)
    })

    Object.keys(groups).forEach(tier => {
        groups[Number(tier)]!.sort((a, b) => {
            const valA = a[sortBy.value] || 0
            const valB = b[sortBy.value] || 0
            return valB - valA
        })
    })

    return groups
})
</script>

<template>
    <div class="flex flex-col gap-6">
        <div class="flex justify-end items-center gap-3 px-2 mb-2">
            <span class="text-xs font-bold text-slate-400 uppercase tracking-widest">Sort by:</span>
            <select v-model="sortBy"
                class="bg-white border border-slate-200 rounded-lg px-3 py-1.5 text-sm font-medium text-slate-600 focus:ring-2 focus:ring-emerald-500 outline-none">
                <option value="hp">HP</option>
                <option value="attack">Attack</option>
                <option value="defense">Defense</option>
                <option value="special_attack">Sp. Atk</option>
                <option value="special_defense">Sp. Defense</option>
                <option value="speed" selected>Speed</option>
            </select>
        </div>

        <div v-for="(group, tier) in groupedResults" :key="tier"
            class="bg-white rounded-2xl border border-slate-200 shadow-sm overflow-hidden">
            <button @click="toggleTier(tier.toString())"
                class="w-full flex items-center justify-between p-5 hover:bg-slate-50 transition-colors"
                :class="{ 'border-b border-slate-100': !collapsedTiers[tier] }">
                <div class="flex items-center gap-4">
                    <svg xmlns="http://www.w3.org/2000/svg"
                        class="h-5 w-5 text-slate-400 transition-transform duration-300"
                        :class="{ '-rotate-90': collapsedTiers[tier] }" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
                    </svg>

                    <div class="flex items-center gap-2">
                        <span
                            class="px-3 py-1 bg-emerald-100 text-emerald-700 text-xs font-black uppercase rounded-full">
                            Tier {{ tier }}
                        </span>
                        <span class="text-slate-700 font-bold uppercase tracking-wide text-sm">
                            {{ tier == '0' ? 'Perfect Walls' : (tier == '1' ? 'Strong Alternatives' : (tier == '2' ?
                                'Only 2 resistances' : (tier == '3' ? 'Only 3 resitances' : 'No resistances'))) }}
                        </span>
                        <span class="text-slate-400 text-xs font-medium">({{ group.length }} found)</span>
                    </div>
                </div>
            </button>

            <div v-show="!collapsedTiers[tier]" class="p-4 bg-slate-50/30">
                <div class="grid grid-cols-1 gap-3">
                    <PokemonDisplay v-for="pokemon in group" :key="pokemon.id" :pokemon="pokemon" />
                </div>
            </div>
        </div>
    </div>
</template>