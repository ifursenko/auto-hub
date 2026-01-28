<template>
  <section>
    <h2>СТО на карте</h2>
    <StationFilter @apply="applyFilter" />
    <YandexMap :stations="stations" />
    <div class="list">
      <div v-for="station in stations" :key="station.id" class="card">
        <h3>{{ station.name }}</h3>
        <p>{{ station.address }}</p>
        <RatingStars :value="station.rating" />
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

<style scoped>
.list {
  display: grid;
  gap: 12px;
  margin-top: 12px;
}
.card {
  border: 1px solid #eee;
  padding: 12px;
}
</style>
