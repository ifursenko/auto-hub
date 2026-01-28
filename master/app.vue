<template>
  <div class="min-h-screen bg-slate-50">
    <header class="sticky top-0 z-30 border-b border-slate-200 bg-white/80 backdrop-blur">
      <div class="mx-auto flex h-16 max-w-6xl items-center justify-between px-4 sm:px-6 lg:px-8">
        <NuxtLink to="/master/dashboard" class="text-lg font-semibold text-slate-900">
          Мастер · АвтоХаб
        </NuxtLink>

        <nav class="hidden items-center gap-6 text-sm font-medium text-slate-600 md:flex">
          <NuxtLink to="/master/login" class="transition hover:text-brand-600">Вход</NuxtLink>
          <NuxtLink to="/master/dashboard" class="transition hover:text-brand-600">Дашборд</NuxtLink>
          <NuxtLink to="/master/schedule" class="transition hover:text-brand-600">Расписание</NuxtLink>
          <NuxtLink to="/master/orders" class="transition hover:text-brand-600">Заказы</NuxtLink>
          <NuxtLink to="/master/profile" class="transition hover:text-brand-600">Профиль</NuxtLink>
        </nav>

        <div class="md:hidden">
          <details class="relative">
            <summary
              class="list-none rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm font-medium text-slate-700 shadow-sm"
            >
              Меню
            </summary>
            <div class="absolute right-0 mt-2 w-48 rounded-xl border border-slate-200 bg-white p-2 shadow-soft">
              <NuxtLink to="/master/login" class="block rounded-lg px-3 py-2 text-sm text-slate-700 hover:bg-slate-100">
                Вход
              </NuxtLink>
              <NuxtLink to="/master/dashboard" class="block rounded-lg px-3 py-2 text-sm text-slate-700 hover:bg-slate-100">
                Дашборд
              </NuxtLink>
              <NuxtLink to="/master/schedule" class="block rounded-lg px-3 py-2 text-sm text-slate-700 hover:bg-slate-100">
                Расписание
              </NuxtLink>
              <NuxtLink to="/master/orders" class="block rounded-lg px-3 py-2 text-sm text-slate-700 hover:bg-slate-100">
                Заказы
              </NuxtLink>
              <NuxtLink to="/master/profile" class="block rounded-lg px-3 py-2 text-sm text-slate-700 hover:bg-slate-100">
                Профиль
              </NuxtLink>
            </div>
          </details>
        </div>
      </div>
    </header>

    <main class="mx-auto w-full max-w-6xl px-4 py-6 sm:px-6 lg:px-8">
      <NuxtPage />
    </main>
  </div>
</template>
