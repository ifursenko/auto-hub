<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Оплата</h2>
      <p class="mt-1 text-sm text-slate-600">Введите номер заказа и подтвердите оплату.</p>
    </div>

    <div class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm">
      <div class="flex flex-col gap-3 sm:flex-row sm:items-center">
        <input
          v-model.number="orderId"
          type="number"
          placeholder="ID заказа"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200 sm:max-w-xs"
        />
        <button
          class="rounded-lg bg-brand-600 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-brand-700"
          @click="pay"
        >
          Оплатить
        </button>
      </div>
    </div>

    <p
      v-if="message"
      class="rounded-lg border border-brand-100 bg-brand-50 px-4 py-2 text-sm text-brand-700"
    >
      {{ message }}
    </p>
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

