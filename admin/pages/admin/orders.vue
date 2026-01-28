<template>
  <section>
    <h2>Все заказы</h2>
    <div v-for="order in orders" :key="order.id" class="card">
      <p>#{{ order.id }} · статус: {{ order.status }}</p>
      <p>Клиент: {{ order.clientId }} · Мастер: {{ order.masterId }}</p>
      <p>Цена: {{ order.totalPrice }} ₽</p>
    </div>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const orders = ref<any[]>([])

onMounted(async () => {
  orders.value = await apiFetch('/api/orders/admin')
})
</script>

<style scoped>
.card {
  border: 1px solid #eee;
  padding: 12px;
  margin-bottom: 12px;
}
</style>
