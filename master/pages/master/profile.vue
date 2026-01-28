<template>
  <section>
    <h2>Профиль мастера</h2>
    <div v-if="profile" class="card">
      <p>Имя: {{ profile.name }}</p>
      <p>Телефон: {{ profile.phone }}</p>
      <p>Роль: {{ profile.role }}</p>
      <input v-model="profileForm.name" placeholder="Новое имя" />
      <button @click="updateProfile">Сохранить</button>
    </div>
    <p v-if="message">{{ message }}</p>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const profile = ref<any>(null)
const profileForm = reactive({ name: '' })
const message = ref('')

const loadProfile = async () => {
  profile.value = await apiFetch('/api/profile')
  profileForm.name = profile.value.name
}

const updateProfile = async () => {
  await apiFetch('/api/profile', {
    method: 'PUT',
    body: JSON.stringify(profileForm)
  })
  message.value = 'Профиль обновлен'
  await loadProfile()
}

onMounted(loadProfile)
</script>

<style scoped>
.card {
  border: 1px solid #eee;
  padding: 12px;
}
</style>
