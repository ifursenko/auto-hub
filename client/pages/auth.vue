<template>
  <section>
    <h2>Вход / регистрация</h2>
    <div class="grid">
      <form class="card" @submit.prevent="register">
        <h3>Регистрация</h3>
        <input v-model="registerForm.phone" placeholder="Телефон" />
        <input v-model="registerForm.name" placeholder="Имя" />
        <select v-model="registerForm.role">
          <option value="CLIENT">Клиент</option>
          <option value="MASTER">Мастер</option>
        </select>
        <button type="submit">Зарегистрироваться</button>
      </form>

      <form class="card" @submit.prevent="login">
        <h3>Вход</h3>
        <input v-model="loginForm.phone" placeholder="Телефон" />
        <button type="submit">Войти</button>
      </form>
    </div>
    <p v-if="message" class="message">{{ message }}</p>
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

<style scoped>
.grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
  gap: 16px;
}
.card {
  display: grid;
  gap: 8px;
  padding: 12px;
  border: 1px solid #eee;
}
.message {
  margin-top: 12px;
  color: #1a7f37;
}
</style>
