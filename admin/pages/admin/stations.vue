<template>
  <section>
    <h2>Управление СТО</h2>
    <div class="card">
      <input v-model="form.name" placeholder="Название" />
      <input v-model="form.address" placeholder="Адрес" />
      <input v-model.number="form.lat" type="number" placeholder="Lat" />
      <input v-model.number="form.lng" type="number" placeholder="Lng" />
      <input v-model="form.serviceIds" placeholder="ID услуг через запятую" />
      <button @click="create">Добавить СТО</button>
    </div>

    <div v-for="station in stations" :key="station.id" class="card">
      <p>{{ station.name }} · {{ station.address }}</p>
      <p>Рейтинг: {{ station.rating }}</p>
    </div>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const stations = ref<any[]>([])
const form = reactive({
  name: '',
  address: '',
  lat: 0,
  lng: 0,
  serviceIds: ''
})

const load = async () => {
  stations.value = await apiFetch('/api/stations')
}

const create = async () => {
  const ids = form.serviceIds
    .split(',')
    .map((value) => value.trim())
    .filter(Boolean)
    .map((value) => Number(value))
  await apiFetch('/api/stations', {
    method: 'POST',
    body: JSON.stringify({
      name: form.name,
      address: form.address,
      lat: form.lat,
      lng: form.lng,
      serviceIds: ids
    })
  })
  await load()
}

onMounted(load)
</script>

<style scoped>
.card {
  border: 1px solid #eee;
  padding: 12px;
  margin-bottom: 12px;
  display: grid;
  gap: 8px;
}
</style>
