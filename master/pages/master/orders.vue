<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">История заказов</h2>
      <p class="mt-1 text-sm text-slate-600">Все выполненные и отмененные заказы.</p>
    </div>
    <div class="grid gap-4">
      <div
        v-for="order in orders"
        :key="order.id"
        class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm"
      >
        <div class="flex flex-wrap items-center justify-between gap-2">
          <p class="text-sm font-semibold text-slate-900">#{{ order.id }}</p>
          <span class="rounded-full bg-slate-100 px-3 py-1 text-xs font-medium text-slate-700">
            {{ order.status }}
          </span>
        </div>
        <div class="mt-3 grid gap-1 text-sm text-slate-600 sm:grid-cols-2">
          <p>Время: {{ order.startTime }} - {{ order.endTime }}</p>
          <p>
            Оценка клиента:
            <span class="font-medium text-slate-900">{{ order.clientRating || '—' }}</span>
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const orders = ref<any[]>([])

onMounted(async () => {
  orders.value = await apiFetch('/api/orders')
})
</script>

