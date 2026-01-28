<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Услуги</h2>
      <p class="mt-1 text-sm text-slate-600">Прозрачные цены и длительность работ.</p>
    </div>

    <ul class="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
      <li
        v-for="service in services"
        :key="service.id"
        class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm"
      >
        <h3 class="text-base font-semibold text-slate-900">{{ service.name }}</h3>
        <p class="mt-2 text-sm text-slate-600">{{ service.description }}</p>
        <div class="mt-4 space-y-1 text-sm text-slate-700">
          <p>Длительность: {{ service.durationMinutes }} мин</p>
          <p>Базовая цена: {{ service.basePrice }} ₽</p>
        </div>
      </li>
    </ul>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const services = ref<any[]>([])

onMounted(async () => {
  services.value = await apiFetch('/api/services')
})
</script>

