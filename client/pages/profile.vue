<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Профиль</h2>
      <p class="mt-1 text-sm text-slate-600">Ваши данные и список автомобилей.</p>
    </div>

    <div
      v-if="profile"
      class="rounded-2xl border border-slate-200 bg-white p-6 shadow-soft"
    >
      <div class="grid gap-2 text-sm text-slate-700">
        <p>
          Имя: <span class="font-medium text-slate-900">{{ profile.name }}</span>
        </p>
        <p>
          Телефон: <span class="font-medium text-slate-900">{{ profile.phone }}</span>
        </p>
        <p>
          Роль: <span class="font-medium text-slate-900">{{ profile.role }}</span>
        </p>
      </div>
      <div class="mt-4 flex flex-col gap-3 sm:flex-row sm:items-center">
        <input
          v-model="profileForm.name"
          placeholder="Новое имя"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200 sm:max-w-xs"
        />
        <button
          class="rounded-lg bg-brand-600 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-brand-700"
          @click="updateProfile"
        >
          Сохранить
        </button>
      </div>
    </div>

    <div class="rounded-2xl border border-slate-200 bg-white p-6 shadow-soft">
      <div class="flex items-center justify-between">
        <h3 class="text-lg font-semibold text-slate-900">Мои автомобили</h3>
      </div>
      <div class="mt-4 grid gap-3 sm:grid-cols-2 lg:grid-cols-5">
        <input
          v-model="carForm.brand"
          placeholder="Марка"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <input
          v-model="carForm.model"
          placeholder="Модель"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <input
          v-model="carForm.year"
          type="number"
          placeholder="Год"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <input
          v-model="carForm.vin"
          placeholder="VIN"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <button
          class="rounded-lg bg-slate-900 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-slate-800"
          @click="addCar"
        >
          Добавить авто
        </button>
      </div>
      <ul class="mt-5 space-y-2 text-sm text-slate-700">
        <li
          v-for="car in cars"
          :key="car.id"
          class="rounded-lg border border-slate-200 bg-slate-50 px-3 py-2"
        >
          {{ car.brand }} {{ car.model }} ({{ car.year }}) · {{ car.vin }}
        </li>
      </ul>
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

