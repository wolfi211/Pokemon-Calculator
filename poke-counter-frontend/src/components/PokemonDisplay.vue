<script setup lang="ts">
import { computed } from 'vue'
import { getTypeHex, getTypeTheme } from '@/constants/TypeColors'
import TypeIcon from './TypeIcon.vue'

const props = defineProps<{
    pokemon: any
    small?: boolean
    closeButton?: boolean
}>()

const normalizedPokemon = computed(() => {
    return {
        name: props.pokemon?.name,
        sprite: props.pokemon?.spriteUrl || props.pokemon?.sprite_url,
        types: props.pokemon?.types || props.pokemon?.type_ids || [],
        hp: props.pokemon?.stats?.hp || props.pokemon?.hp,
        atk: props.pokemon?.stats?.attack || props.pokemon?.attack,
        def: props.pokemon?.stats?.defense || props.pokemon?.defense,
        spa: props.pokemon?.stats?.specialAttack || props.pokemon?.special_attack,
        spd: props.pokemon?.stats?.specialDefense || props.pokemon?.special_defense,
        spe: props.pokemon?.stats?.speed || props.pokemon?.speed,
    }
})

const bulbapediaUrl = computed(() => {
    const species = props.pokemon.species_name || props.pokemon.name

    const hardMapping: Record<string, string> = {
        'porygon-z': 'Porygon-Z',
        'type-null': 'Type:_Null'
    }

    if (hardMapping[species]) {
        return `https://bulbapedia.bulbagarden.net/wiki/${hardMapping[species]}_(Pok%C3%A9mon)`
    }

    const parts = species.split('-')

    const capParts = parts.map((p: string) => {
        if (p.toLowerCase() === 'o') return 'o'
        return p.charAt(0).toUpperCase() + p.slice(1)
    })

    const underscorePrefixes = [
        'Iron', 'Great', 'Scream', 'Brute', 'Flutter', 'Sandy',
        'Roaring', 'Walking', 'Gouging', 'Raging', 'Tapu'
    ]

    const joiner = underscorePrefixes.includes(capParts[0]) ? '_' : '-'
    const finalName = capParts.join(joiner)

    return `https://bulbapedia.bulbagarden.net/wiki/${finalName}_(Pok%C3%A9mon)`
})

const cardStyle = computed(() => {
    const types = normalizedPokemon.value.types
    if (!types.length) return {}

    const type1Id = types[0]
    const type2Id = types[1] || type1Id
    const color1 = getTypeHex(type1Id)
    const color2 = getTypeHex(type2Id)

    return {
        'background-image': `linear-gradient(white, white), linear-gradient(135deg, ${color1} 50%, ${color2} 50%)`,
        'background-origin': 'border-box',
        'background-clip': 'padding-box, border-box',
        'border': '5px solid transparent'
    }
})

const typeIcons = computed(() => {
    return normalizedPokemon.value.types.map((id: number) => ({
        url: new URL(`/src/assets/images/types/${id}.png`, window.location.origin).href,
        theme: getTypeTheme(id)
    }))
})
</script>

<template>
    <div v-if="pokemon"
        class="group relative flex items-center bg-white rounded-xl shadow-sm hover:shadow-md transition-shadow"
        :class="small ? 'p-1' : 'p-4'" :style="cardStyle">

        <a :href="bulbapediaUrl" target="_blank" rel="noopener noreferrer"
            class="absolute top-3 right-3 p-1.5 rounded-md text-neutral-400 hover:text-emerald-600 hover:bg-emerald-50 transition-all opacity-0 group-hover:opacity-100"
            :class="small ? 'group-hover:bg-emerald-100/70' : ''" title="View on Bulbapedia">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14" />
            </svg>
        </a>

        <div class="flex flex-col items-center" :class="small ? 'mx-0' : 'mx-5 min-w-30'">
            <div class="relative overflow-hidden hover:overflow-visible z-100 flex items-center justify-center"
                :class="small ? 'w-15 h-15' : 'w-24 h-24'">
                <img :src="normalizedPokemon.sprite" :alt="normalizedPokemon.name"
                    class="w-full h-full object-contain transform scale-[1] transition-transform hover:scale-[1.3]" />
            </div>

            <h4 v-if="!small" class="capitalize font-bold text-neutral-800 text-center text-lg">{{
                normalizedPokemon.name }}</h4>
        </div>

        <div v-if="!small" class="flex flex-col gap-1 h-full">
            <TypeIcon v-for="(icon, index) in typeIcons" :key="index" :url="icon.url" :type-theme="icon.theme" />
        </div>

        <div class="grid grid-cols-6 gap-2 w-full text-center pr-4">
            <div v-for="stat in [
                { label: 'HP', val: normalizedPokemon.hp },
                { label: 'Atk', val: normalizedPokemon.atk },
                { label: 'Def', val: normalizedPokemon.def },
                { label: 'SpA', val: normalizedPokemon.spa },
                { label: 'SpD', val: normalizedPokemon.spd },
                { label: 'Spe', val: normalizedPokemon.spe }
            ]" :key="stat.label">
                <span class="block text-neutral-400 uppercase font-bold" :class="!small ? 'text-lg' : 'text-xs'">{{
                    stat.label }}</span>
                <span class="font-semibold text-neutral-700" :class="!small ? 'text-xl' : 'text-xs'">{{ stat.val
                    }}</span>
            </div>
        </div>
    </div>
</template>