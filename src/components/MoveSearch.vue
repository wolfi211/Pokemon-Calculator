<script setup lang="ts">

defineProps({
    order: Number
})
</script>

<template>
    <div class="h-fit border border-teal-500 p-3 rounded-md">
        Move Search {{ order }}
    </div>
</template>