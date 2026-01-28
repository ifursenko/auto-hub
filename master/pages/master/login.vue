<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Вход мастера</h2>
      <p class="mt-1 text-sm text-slate-600">Введите номер телефона для входа.</p>
    </div>
    <form
      class="max-w-md rounded-2xl border border-slate-200 bg-white p-6 shadow-soft"
      @submit.prevent="login"
    >
      <div class="grid gap-3">
        <input
          v-model="phone"
          placeholder="Телефон"
          class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
        />
        <button
          type="submit"
          class="rounded-lg bg-brand-600 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-brand-700"
        >
          Войти
        </button>
      </div>
    </form>
    <p
      v-if="message"
      class="rounded-lg border border-brand-100 bg-brand-50 px-4 py-2 text-sm text-brand-700"
    >
      {{ message }}
    </p>
  </section>
</template>

<script setup lang="ts">
const { apiFetch, token } = useApi()
const phone = ref('')
const message = ref('')

const login = async () => {
  try {
    const res = await apiFetch<{ token: string }>('/api/login', {
      method: 'POST',
      body: JSON.stringify({ phone })
    })
    token.value = res.token
    message.value = 'Успешный вход'
    navigateTo('/master/dashboard')
  } catch (err: any) {
    message.value = err.message
  }
}
</script>

