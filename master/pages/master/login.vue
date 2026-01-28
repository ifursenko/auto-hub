<template>
  <section>
    <h2>Вход мастера</h2>
    <form class="card" @submit.prevent="login">
      <input v-model="phone" placeholder="Телефон" />
      <button type="submit">Войти</button>
    </form>
    <p v-if="message">{{ message }}</p>
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

<style scoped>
.card {
  display: grid;
  gap: 8px;
  border: 1px solid #eee;
  padding: 12px;
  max-width: 280px;
}
</style>
