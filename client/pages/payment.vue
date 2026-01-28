<template>
  <section>
    <h2>Оплата</h2>
    <div class="card">
      <input v-model.number="orderId" type="number" placeholder="ID заказа" />
      <button @click="pay">Оплатить</button>
    </div>
    <p v-if="message">{{ message }}</p>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const orderId = ref<number | null>(null)
const message = ref('')

const pay = async () => {
  if (!orderId.value) return
  await apiFetch(`/api/orders/${orderId.value}/pay`, { method: 'POST' })
  message.value = 'Оплата прошла успешно'
}
</script>

<style scoped>
.card {
  border: 1px solid #eee;
  padding: 12px;
  display: flex;
  gap: 8px;
}
</style>
