<template>
  <div class="orders">
    <div v-for="order in orders" :key="order.id" class="card">
      <p>Заказ #{{ order.id }} · статус: {{ order.status }}</p>
      <p>Мастер: {{ order.masterId }} · Авто: {{ order.carId }}</p>
      <p>Время: {{ order.startTime }} - {{ order.endTime }}</p>
      <p>Цена: {{ order.totalPrice }} ₽</p>
      <p v-if="order.clientRating">Оценка: {{ order.clientRating }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{ orders: any[] }>()
</script>

<style scoped>
.card {
  border: 1px solid #eee;
  padding: 12px;
  margin-bottom: 12px;
}
</style>
