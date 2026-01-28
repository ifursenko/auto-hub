<template>
  <section class="space-y-6">
    <div class="rounded-2xl border border-slate-200 bg-white p-6 shadow-soft">
      <div class="flex flex-col gap-6 md:flex-row md:items-center md:justify-between">
        <div class="space-y-2">
          <p class="text-sm font-semibold text-brand-600">Сервисный маркетплейс</p>
          <h2 class="text-2xl font-semibold text-slate-900 sm:text-3xl">
            Поиск СТО и услуг
          </h2>
          <p class="max-w-xl text-sm text-slate-600">
            Быстро найдите сервис по бренду, услуге или району и забронируйте время онлайн.
          </p>
        </div>
        <div class="flex w-full flex-col gap-2 sm:flex-row md:max-w-md">
          <input
            v-model="query"
            placeholder="Марка, услуга, район"
            class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm text-slate-900 shadow-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
          />
          <button
            class="rounded-lg bg-brand-600 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-brand-700"
            @click="onSearch"
          >
            Найти
          </button>
        </div>
      </div>
    </div>

    <div class="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
      <NuxtLink
        to="/services"
        class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm transition hover:-translate-y-0.5 hover:shadow-md"
      >
        <h3 class="text-base font-semibold text-slate-900">Каталог услуг</h3>
        <p class="mt-2 text-sm text-slate-600">Подборка работ с ценами и длительностью.</p>
      </NuxtLink>
      <NuxtLink
        to="/stations"
        class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm transition hover:-translate-y-0.5 hover:shadow-md"
      >
        <h3 class="text-base font-semibold text-slate-900">Карта СТО</h3>
        <p class="mt-2 text-sm text-slate-600">Найдите ближайшую станцию и рейтинг.</p>
      </NuxtLink>
      <NuxtLink
        to="/booking"
        class="rounded-2xl border border-slate-200 bg-white p-5 shadow-sm transition hover:-translate-y-0.5 hover:shadow-md"
      >
        <h3 class="text-base font-semibold text-slate-900">Онлайн запись</h3>
        <p class="mt-2 text-sm text-slate-600">Выберите мастера и свободный слот.</p>
      </NuxtLink>
    </div>
  </section>
</template>

<script setup lang="ts">
const query = ref('')
const onSearch = () => {
  navigateTo(`/stations?query=${encodeURIComponent(query.value)}`)
}
</script>

