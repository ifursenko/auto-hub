<template>
  <div class="map">
    <p>Карта (Yandex Maps API)</p>
    <ul>
      <li v-for="station in stations" :key="station.id">
        {{ station.name }} · {{ station.lat }}, {{ station.lng }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
defineProps<{ stations: any[] }>()
</script>

<style scoped>
.map {
  border: 1px dashed #ccc;
  padding: 12px;
  margin-bottom: 12px;
}
</style>
