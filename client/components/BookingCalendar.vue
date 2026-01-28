<template>
  <div class="calendar">
    <div v-if="slots.length === 0">Нет доступных слотов</div>
    <button
      v-for="slot in slots"
      :key="slot.startTime"
      class="slot"
      @click="$emit('select', slot)"
    >
      {{ slot.startTime }} - {{ slot.endTime }}
    </button>
  </div>
</template>

<script setup lang="ts">
defineProps<{ slots: any[] }>()
defineEmits<{
  (e: 'select', slot: any): void
}>()
</script>

<style scoped>
.calendar {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.slot {
  padding: 6px 10px;
  border: 1px solid #ddd;
  background: #fff;
}
</style>
