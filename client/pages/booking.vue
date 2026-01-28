<template>
  <section>
    <h2>Бронирование</h2>
    <div class="grid">
      <input v-model.number="form.masterId" type="number" placeholder="ID мастера" />
      <input v-model.number="form.serviceId" type="number" placeholder="ID услуги" />
      <input v-model.number="form.carId" type="number" placeholder="ID авто" />
      <input v-model="form.date" type="date" />
      <button @click="loadSlots">Показать слоты</button>
    </div>

    <BookingCalendar :slots="slots" @select="selectSlot" />

    <div v-if="selectedSlot" class="card">
      <p>Выбранное время: {{ selectedSlot.startTime }}</p>
      <button @click="createOrder">Забронировать</button>
    </div>
    <p v-if="message">{{ message }}</p>
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

<style scoped>
.grid {
  display: grid;
  gap: 8px;
  grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
  margin-bottom: 12px;
}
.card {
  border: 1px solid #eee;
  padding: 12px;
  margin-top: 12px;
}
</style>
