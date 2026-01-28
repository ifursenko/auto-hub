<template>
  <section>
    <h2>Заказы на сегодня</h2>
    <div v-for="order in orders" :key="order.id" class="card">
      <p>#{{ order.id }} · статус: {{ order.status }}</p>
      <p>Время: {{ order.startTime }} - {{ order.endTime }}</p>
      <p>Клиент: {{ order.clientId }}</p>
      <div class="actions">
        <button @click="setStatus(order.id, 'ACCEPTED')">Принять</button>
        <button @click="setStatus(order.id, 'IN_PROGRESS')">В работе</button>
        <button @click="setStatus(order.id, 'READY')">Готов</button>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const orders = ref<any[]>([])

const load = async () => {
  orders.value = await apiFetch('/api/orders')
}

const setStatus = async (id: number, status: string) => {
  await apiFetch(`/api/orders/${id}/status`, {
    method: 'PUT',
    body: JSON.stringify({ status })
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
}
.actions {
  display: flex;
  gap: 8px;
}
</style>
