<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Расписание</h2>
      <p class="mt-1 text-sm text-slate-600">Просмотр доступных слотов по дате.</p>
    </div>
    <div class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm">
      <div class="grid gap-3 sm:grid-cols-2 lg:grid-cols-3">
        <input
          v-model.number="masterId"
          type="number"
          placeholder="ID мастера"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <input
          v-model="date"
          type="date"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <button
          class="rounded-lg bg-brand-600 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-brand-700"
          @click="load"
        >
          Показать
        </button>
      </div>
    </div>
    <div class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm">
      <h3 class="text-sm font-semibold text-slate-700">Слоты</h3>
      <ul class="mt-3 space-y-2 text-sm text-slate-700">
        <li
          v-for="slot in slots"
          :key="slot.startTime"
          class="rounded-lg border border-slate-200 bg-slate-50 px-3 py-2"
        >
          {{ slot.startTime }} - {{ slot.endTime }}
        </li>
      </ul>
    </div>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const masterId = ref<number>(1)
const date = ref(new Date().toISOString().slice(0, 10))
const slots = ref<any[]>([])

const load = async () => {
  slots.value = await apiFetch(`/api/masters/${masterId.value}/schedule?date=${date.value}`)
}
</script>

