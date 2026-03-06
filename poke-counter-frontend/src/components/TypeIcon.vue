<script setup lang="ts">
import { type TypeTheme } from '@/constants/TypeColors'

const props = defineProps<{
    typeTheme: TypeTheme,
    url: string
}>()
</script>

<template>
    <div class="w-6 h-6 rounded-lg" :class="typeTheme.bg">
        <img :src="url" class="w-6 h-6 object-contain" />
    </div>
</template>