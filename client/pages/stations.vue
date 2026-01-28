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
        <YandexMap :stations="stations" />
      </div>

      <div class="space-y-3">
        <div
          v-for="station in stations"
          :key="station.id"
          class="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm"
        >
          <h3 class="text-base font-semibold text-slate-900">{{ station.name }}</h3>
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

const loadStations = async (serviceId?: number, minRating?: number) => {
  const params = new URLSearchParams()
  if (serviceId) params.set('serviceId', String(serviceId))
  if (minRating) params.set('minRating', String(minRating))
  const query = params.toString() ? `?${params}` : ''
  stations.value = await apiFetch(`/api/stations${query}`)
}

const applyFilter = async (payload: { serviceId?: number; minRating?: number }) => {
  await loadStations(payload.serviceId, payload.minRating)
}

onMounted(async () => {
  await loadStations()
})
</script>

