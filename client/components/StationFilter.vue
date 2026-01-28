<template>
  <div class="flex flex-col gap-3 sm:flex-row sm:items-end">
    <label class="flex-1 text-sm text-slate-600">
      ID услуги
      <input
        v-model.number="serviceId"
        type="number"
        placeholder="Например 12"
        class="mt-1 w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm text-slate-900 focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
      />
    </label>
    <label class="flex-1 text-sm text-slate-600">
      Мин. рейтинг
      <input
        v-model.number="minRating"
        type="number"
        step="0.1"
        placeholder="Например 4.5"
        class="mt-1 w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm text-slate-900 focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
      />
    </label>
    <button
      class="rounded-lg bg-slate-900 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-slate-800"
      @click="apply"
    >
      Применить
    </button>
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

