<template>
  <div class="space-y-3">
    <div class="flex items-center justify-between">
      <p class="text-sm font-semibold text-slate-700">Карта (Yandex Maps API)</p>
      <span class="rounded-full bg-slate-100 px-3 py-1 text-xs text-slate-600">
        Заглушка
      </span>
    </div>
    <div
      class="rounded-2xl border border-dashed border-slate-200 bg-slate-50 p-6 text-sm text-slate-500"
    >
      Карта будет доступна после подключения API.
    </div>
    <ul class="space-y-2 text-sm text-slate-600">
      <li
        v-for="station in stations"
        :key="station.id"
        class="rounded-lg border border-slate-200 bg-white px-3 py-2"
      >
        {{ station.name }} · {{ station.lat }}, {{ station.lng }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
defineProps<{ stations: any[] }>()
</script>

