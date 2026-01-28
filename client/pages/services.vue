<template>
  <section>
    <h2>Услуги</h2>
    <ul>
      <li v-for="service in services" :key="service.id" class="card">
        <h3>{{ service.name }}</h3>
        <p>{{ service.description }}</p>
        <p>Длительность: {{ service.durationMinutes }} мин</p>
        <p>Базовая цена: {{ service.basePrice }} ₽</p>
      </li>
    </ul>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const services = ref<any[]>([])

onMounted(async () => {
  services.value = await apiFetch('/api/services')
})
</script>

<style scoped>
.card {
  border: 1px solid #eee;
  padding: 12px;
  margin-bottom: 12px;
}
</style>
