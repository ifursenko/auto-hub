<template>
  <section>
    <h2>Расписание</h2>
    <div class="controls">
      <input v-model.number="masterId" type="number" placeholder="ID мастера" />
      <input v-model="date" type="date" />
      <button @click="load">Показать</button>
    </div>
    <ul>
      <li v-for="slot in slots" :key="slot.startTime">
        {{ slot.startTime }} - {{ slot.endTime }}
      </li>
    </ul>
  </section>
</template>

<script setup lang="ts">
const { apiFetch } = useApi()
const masterId = ref<number>(1)
const date = ref(new Date().toISOString().slice(0, 10))
const slots = ref<any[]>([])

const load = async () => {
  slots.value = await apiFetch(`/api/masters/${masterId.value}/schedule?date=${date.value}`)
}
</script>

<style scoped>
.controls {
  display: flex;
  gap: 8px;
  margin-bottom: 12px;
}
</style>
