<template>
  <div class="flex flex-wrap gap-2">
    <div
      v-if="slots.length === 0"
      class="w-full rounded-lg border border-dashed border-slate-200 bg-slate-50 px-3 py-4 text-center text-sm text-slate-500"
    >
      Нет доступных слотов
    </div>
    <button
      v-for="slot in slots"
      :key="slot.startTime"
      class="rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm text-slate-700 shadow-sm transition hover:border-brand-300 hover:text-brand-700"
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

