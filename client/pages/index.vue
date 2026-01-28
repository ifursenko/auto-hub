<template>
  <section>
    <h2>Поиск СТО и услуг</h2>
    <div class="search">
      <input v-model="query" placeholder="Марка, услуга, район" />
      <button @click="onSearch">Найти</button>
    </div>
    <p class="hint">Используйте каталог услуг или карту для выбора СТО.</p>
  </section>
</template>

<script setup lang="ts">
const query = ref('')
const onSearch = () => {
  navigateTo(`/stations?query=${encodeURIComponent(query.value)}`)
}
</script>

<style scoped>
.search {
  display: flex;
  gap: 8px;
}
.hint {
  margin-top: 8px;
  color: #666;
}
</style>
