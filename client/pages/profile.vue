<template>
  <section>
    <h2>Профиль</h2>
    <div v-if="profile" class="card">
      <p>Имя: {{ profile.name }}</p>
      <p>Телефон: {{ profile.phone }}</p>
      <p>Роль: {{ profile.role }}</p>
      <input v-model="profileForm.name" placeholder="Новое имя" />
      <button @click="updateProfile">Сохранить</button>
    </div>

    <div class="card">
      <h3>Мои автомобили</h3>
      <div class="grid">
        <input v-model="carForm.brand" placeholder="Марка" />
        <input v-model="carForm.model" placeholder="Модель" />
        <input v-model="carForm.year" type="number" placeholder="Год" />
        <input v-model="carForm.vin" placeholder="VIN" />
        <button @click="addCar">Добавить авто</button>
      </div>
      <ul>
        <li v-for="car in cars" :key="car.id">
          {{ car.brand }} {{ car.model }} ({{ car.year }}) · {{ car.vin }}
        </li>
      </ul>
    </div>
    <p v-if="message" class="message">{{ message }}</p>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const profile = ref<any>(null)
const cars = ref<any[]>([])
const message = ref('')
const profileForm = reactive({ name: '' })
const carForm = reactive({ brand: '', model: '', year: 2020, vin: '' })

const loadProfile = async () => {
  profile.value = await apiFetch('/api/profile')
  profileForm.name = profile.value.name
}

const loadCars = async () => {
  cars.value = await apiFetch('/api/cars')
}

const updateProfile = async () => {
  await apiFetch('/api/profile', {
    method: 'PUT',
    body: JSON.stringify(profileForm)
  })
  message.value = 'Профиль обновлен'
  await loadProfile()
}

const addCar = async () => {
  await apiFetch('/api/cars', {
    method: 'POST',
    body: JSON.stringify({ ...carForm, year: Number(carForm.year) })
  })
  message.value = 'Авто добавлено'
  await loadCars()
}

onMounted(async () => {
  await loadProfile()
  await loadCars()
})
</script>

<style scoped>
.card {
  padding: 12px;
  border: 1px solid #eee;
  margin-bottom: 12px;
}
.grid {
  display: grid;
  gap: 8px;
  grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
  margin-bottom: 8px;
}
.message {
  color: #1a7f37;
}
</style>
