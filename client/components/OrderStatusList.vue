<template>
  <div class="grid gap-4">
    <div
      v-for="order in orders"
      :key="order.id"
      class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm"
    >
      <div class="flex flex-wrap items-center justify-between gap-2">
        <p class="text-sm font-semibold text-slate-900">Заказ #{{ order.id }}</p>
        <span class="rounded-full px-3 py-1 text-xs font-medium" :class="statusClass(order.status)">
          {{ order.status }}
        </span>
      </div>
      <div class="mt-3 grid gap-1 text-sm text-slate-600 sm:grid-cols-2">
        <p>Мастер: <span class="font-medium text-slate-900">{{ order.masterId }}</span></p>
        <p>Авто: <span class="font-medium text-slate-900">{{ order.carId }}</span></p>
        <p>Время: {{ order.startTime }} - {{ order.endTime }}</p>
        <p>Цена: <span class="font-medium text-slate-900">{{ order.totalPrice }} ₽</span></p>
      </div>
      <p v-if="order.clientRating" class="mt-3 text-sm text-slate-700">
        Оценка: <span class="font-medium text-slate-900">{{ order.clientRating }}</span>
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{ orders: any[] }>()

const statusClass = (status: string) => {
  switch (status) {
    case 'NEW':
      return 'bg-blue-50 text-blue-700'
    case 'CONFIRMED':
      return 'bg-emerald-50 text-emerald-700'
    case 'IN_PROGRESS':
      return 'bg-amber-50 text-amber-700'
    case 'DONE':
      return 'bg-slate-100 text-slate-700'
    case 'CANCELLED':
      return 'bg-rose-50 text-rose-700'
    default:
      return 'bg-slate-100 text-slate-700'
  }
}
</script>
