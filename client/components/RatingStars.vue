<template>
  <div class="stars">
    <span v-for="n in 5" :key="n">
      {{ n <= rounded ? '★' : '☆' }}
    </span>
    <span class="value">{{ value.toFixed(1) }}</span>
  </div>
</template>

<script setup lang="ts">
const props = defineProps<{ value: number }>()
const rounded = computed(() => Math.round(props.value))
</script>

<style scoped>
.stars {
  color: #f5a623;
  display: flex;
  gap: 4px;
  align-items: center;
}
.value {
  color: #444;
  font-size: 12px;
}
</style>
