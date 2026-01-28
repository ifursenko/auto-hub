<template>
  <div class="flex items-center gap-1 text-amber-400">
    <span v-for="n in 5" :key="n" class="text-base">
      {{ n <= rounded ? '★' : '☆' }}
    </span>
    <span class="ml-1 text-xs text-slate-500">{{ value.toFixed(1) }}</span>
  </div>
</template>

<script setup lang="ts">
const props = defineProps<{ value: number }>()
const rounded = computed(() => Math.round(props.value))
</script>

