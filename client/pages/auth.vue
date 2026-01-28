<template>
  <section class="space-y-6">
    <div>
      <h2 class="text-2xl font-semibold text-slate-900">Вход и регистрация</h2>
      <p class="mt-1 text-sm text-slate-600">
        Создайте аккаунт или войдите по номеру телефона.
      </p>
    </div>

    <div class="grid gap-6 lg:grid-cols-2">
      <form
        class="rounded-2xl border border-slate-200 bg-white p-6 shadow-soft"
        @submit.prevent="register"
      >
        <div class="space-y-1">
          <h3 class="text-lg font-semibold text-slate-900">Регистрация</h3>
          <p class="text-sm text-slate-500">Доступ к заказам и истории обслуживания.</p>
        </div>
        <div class="mt-5 grid gap-3">
          <input
            v-model="registerForm.phone"
            placeholder="Телефон"
            class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
          />
          <input
            v-model="registerForm.name"
            placeholder="Имя"
            class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
          />
          <select
            v-model="registerForm.role"
            class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
          >
            <option value="CLIENT">Клиент</option>
            <option value="MASTER">Мастер</option>
          </select>
          <button
            type="submit"
            class="mt-2 rounded-lg bg-brand-600 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-brand-700"
          >
            Зарегистрироваться
          </button>
        </div>
      </form>

      <form
        class="rounded-2xl border border-slate-200 bg-white p-6 shadow-soft"
        @submit.prevent="login"
      >
        <div class="space-y-1">
          <h3 class="text-lg font-semibold text-slate-900">Вход</h3>
          <p class="text-sm text-slate-500">Введите номер телефона для входа.</p>
        </div>
        <div class="mt-5 grid gap-3">
          <input
            v-model="loginForm.phone"
            placeholder="Телефон"
            class="w-full rounded-lg border border-slate-200 bg-white px-3 py-2 text-sm focus:border-brand-500 focus:outline-none focus:ring-2 focus:ring-brand-200"
          />
          <button
            type="submit"
            class="mt-2 rounded-lg bg-slate-900 px-4 py-2 text-sm font-medium text-white shadow-sm transition hover:bg-slate-800"
          >
            Войти
          </button>
        </div>
      </form>
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
const { apiFetch, token } = useApi()
const message = ref('')
const registerForm = reactive({
  phone: '',
  name: '',
  role: 'CLIENT'
})
const loginForm = reactive({ phone: '' })

const register = async () => {
  try {
    const res = await apiFetch<{ token: string }>('/api/register', {
      method: 'POST',
      body: JSON.stringify(registerForm)
    })
    token.value = res.token
    message.value = 'Регистрация успешна'
  } catch (err: any) {
    message.value = err.message
  }
}

const login = async () => {
  try {
    const res = await apiFetch<{ token: string }>('/api/login', {
      method: 'POST',
      body: JSON.stringify(loginForm)
    })
    token.value = res.token
    message.value = 'Вход успешен'
  } catch (err: any) {
    message.value = err.message
  }
}
</script>

