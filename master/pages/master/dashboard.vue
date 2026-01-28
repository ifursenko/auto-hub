<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Заказы на сегодня</h2>
      <p class="mt-1 text-sm text-slate-600">Управляйте статусами заказов.</p>
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
          <p>Клиент: <span class="font-medium text-slate-900">{{ order.clientId }}</span></p>
        </div>
        <div class="mt-4 flex flex-wrap gap-2">
          <button
            class="rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-700 transition hover:border-brand-200 hover:text-brand-700"
            @click="setStatus(order.id, 'ACCEPTED')"
          >
            Принять
          </button>
          <button
            class="rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-700 transition hover:border-brand-200 hover:text-brand-700"
            @click="setStatus(order.id, 'IN_PROGRESS')"
          >
            В работе
          </button>
          <button
            class="rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-700 transition hover:border-brand-200 hover:text-brand-700"
            @click="setStatus(order.id, 'READY')"
          >
            Готов
          </button>
        </div>
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

