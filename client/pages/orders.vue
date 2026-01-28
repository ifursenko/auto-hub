<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Мои заказы</h2>
      <p class="mt-1 text-sm text-slate-600">История обслуживания и статусы.</p>
    </div>
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
