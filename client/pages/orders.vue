<template>
  <section>
    <h2>Мои заказы</h2>
    <OrderStatusList :orders="orders" />
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const orders = ref<any[]>([])

onMounted(async () => {
  orders.value = await apiFetch('/api/orders')
})
</script>
