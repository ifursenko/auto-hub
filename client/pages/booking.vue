<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Бронирование</h2>
      <p class="mt-1 text-sm text-slate-600">
        Выберите мастера, услугу и доступный слот.
      </p>
    </div>

    <div class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm">
      <div class="grid gap-3 sm:grid-cols-2 lg:grid-cols-5">
        <input
          v-model.number="form.masterId"
          type="number"
          placeholder="ID мастера"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <input
          v-model.number="form.serviceId"
          type="number"
          placeholder="ID услуги"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <input
          v-model.number="form.carId"
          type="number"
          placeholder="ID авто"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <input
          v-model="form.date"
          type="date"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <button
          class="rounded-lg bg-brand-600 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-brand-700"
          @click="loadSlots"
        >
          Показать слоты
        </button>
      </div>
    </div>

    <div class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm">
      <h3 class="text-sm font-semibold text-slate-700">Доступные слоты</h3>
      <div class="mt-3">
        <BookingCalendar :slots="slots" @select="selectSlot" />
      </div>
    </div>

    <div
      v-if="selectedSlot"
      class="flex flex-col gap-3 rounded-2xl border border-slate-200 bg-white p-5 shadow-sm sm:flex-row sm:items-center sm:justify-between"
    >
      <p class="text-sm text-slate-700">
        Выбранное время: <span class="font-medium text-slate-900">{{ selectedSlot.startTime }}</span>
      </p>
      <button
        class="rounded-lg bg-slate-900 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-slate-800"
        @click="createOrder"
      >
        Забронировать
      </button>
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
const slots = ref<any[]>([])
const selectedSlot = ref<any>(null)
const message = ref('')
const form = reactive({
  masterId: 1,
  serviceId: 1,
  carId: 1,
  date: new Date().toISOString().slice(0, 10)
})

const loadSlots = async () => {
  selectedSlot.value = null
  const res = await apiFetch(`/api/masters/${form.masterId}/schedule?date=${form.date}`)
  slots.value = res
}

const selectSlot = (slot: any) => {
  selectedSlot.value = slot
}

const createOrder = async () => {
  await apiFetch('/api/orders', {
    method: 'POST',
    body: JSON.stringify({
      masterId: form.masterId,
      serviceId: form.serviceId,
      carId: form.carId,
      startTime: selectedSlot.value.startTime
    })
  })
  message.value = 'Заказ создан'
}
</script>

