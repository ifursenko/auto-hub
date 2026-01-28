<template>
  <section>
    <h2>Управление услугами</h2>
    <div class="card">
      <input v-model="form.name" placeholder="Название" />
      <input v-model="form.description" placeholder="Описание" />
      <input v-model.number="form.durationMinutes" type="number" placeholder="Длительность (мин)" />
      <input v-model.number="form.basePrice" type="number" placeholder="Базовая цена" />
      <button @click="create">Добавить услугу</button>
    </div>

    <div v-for="service in services" :key="service.id" class="card">
      <p>{{ service.name }} · {{ service.durationMinutes }} мин</p>
      <p>{{ service.basePrice }} ₽</p>
      <div class="actions">
        <button @click="remove(service.id)">Удалить</button>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const services = ref<any[]>([])
const form = reactive({
  name: '',
  description: '',
  durationMinutes: 30,
  basePrice: 500
})

const load = async () => {
  services.value = await apiFetch('/api/services')
}

const create = async () => {
  await apiFetch('/api/services', {
    method: 'POST',
    body: JSON.stringify(form)
  })
  await load()
}

const remove = async (id: number) => {
  await apiFetch(`/api/services/${id}`, { method: 'DELETE' })
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
.actions {
  display: flex;
  gap: 8px;
}
</style>
