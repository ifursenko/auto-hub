<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">СТО на карте</h2>
      <p class="mt-1 text-sm text-slate-600">
        Подберите станцию по услуге и рейтингу.
      </p>
    </div>

    <div class="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
      <StationFilter @apply="applyFilter" />
    </div>

    <div class="grid gap-6 lg:grid-cols-[1.2fr_1fr]">
      <div class="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
        <YandexMap :stations="stations" :selected-id="selectedStationId" @select="selectStation" />
      </div>

      <div class="space-y-3">
        <div
          v-for="station in stations"
          :key="station.id"
          class="cursor-pointer rounded-2xl border border-slate-200 bg-white p-4 shadow-sm transition hover:border-brand-200"
          :class="station.id === selectedStationId ? 'ring-2 ring-brand-200' : ''"
          @click="selectStation(station)"
        >
          <div class="flex items-center justify-between gap-2">
            <h3 class="text-base font-semibold text-slate-900">{{ station.name }}</h3>
            <span
              v-if="station.id === selectedStationId"
              class="rounded-full bg-brand-50 px-2 py-1 text-xs font-medium text-brand-700"
            >
              Выбрано
            </span>
          </div>
          <p class="mt-1 text-sm text-slate-600">{{ station.address }}</p>
          <div class="mt-3">
            <RatingStars :value="station.rating" />
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const stations = ref<any[]>([])
const selectedStationId = ref<number | null>(null)

const loadStations = async (serviceId?: number, minRating?: number) => {
  const params = new URLSearchParams()
  if (serviceId) params.set('serviceId', String(serviceId))
  if (minRating) params.set('minRating', String(minRating))
  const query = params.toString() ? `?${params}` : ''
  stations.value = await apiFetch(`/api/stations${query}`)
  if (!stations.value.some((station) => station.id === selectedStationId.value)) {
    selectedStationId.value = null
  }
}

const applyFilter = async (payload: { serviceId?: number; minRating?: number }) => {
  await loadStations(payload.serviceId, payload.minRating)
}

const selectStation = (station: any) => {
  selectedStationId.value = station?.id ?? null
}

onMounted(async () => {
  await loadStations()
})
</script>

