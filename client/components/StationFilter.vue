<template>
  <div class="filter">
    <input v-model.number="serviceId" type="number" placeholder="ID услуги" />
    <input v-model.number="minRating" type="number" step="0.1" placeholder="Мин. рейтинг" />
    <button @click="apply">Применить</button>
  </div>
</template>

<script setup lang="ts">
const emit = defineEmits<{
  (e: 'apply', payload: { serviceId?: number; minRating?: number }): void
}>()

const serviceId = ref<number | undefined>()
const minRating = ref<number | undefined>()

const apply = () => {
  emit('apply', { serviceId: serviceId.value, minRating: minRating.value })
}
</script>

<style scoped>
.filter {
  display: flex;
  gap: 8px;
  margin-bottom: 12px;
}
</style>
