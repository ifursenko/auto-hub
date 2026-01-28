<template>
  <div class="space-y-3">
    <div class="flex items-center justify-between">
      <p class="text-sm font-semibold text-slate-700">Карта СТО</p>
      <span class="rounded-full bg-slate-100 px-3 py-1 text-xs text-slate-600">
        OpenStreetMap
      </span>
    </div>

    <div
      ref="mapEl"
      class="h-72 w-full rounded-2xl border border-slate-200 bg-slate-100"
    ></div>

    <p v-if="!hasMarkers" class="text-xs text-slate-500">
      Координаты станций не найдены — карта без маркеров.
    </p>
  </div>
</template>

<script setup lang="ts">
const props = defineProps<{ stations: any[]; selectedId?: number | null }>()
const emit = defineEmits<{
  (e: 'select', station: any): void
}>()

const mapEl = ref<HTMLDivElement | null>(null)
const mapReady = ref(false)
const hasMarkers = ref(false)

let mapInstance: any = null
let markerLayer: any[] = []

const defaultCenter: [number, number] = [55.751244, 37.618423]

const getCoords = (station: any) => {
  const lat = Number(station?.lat)
  const lng = Number(station?.lng)
  if (Number.isFinite(lat) && Number.isFinite(lng)) return [lat, lng] as [number, number]
  return null
}

const resetMarkers = (L: any) => {
  markerLayer.forEach((marker) => marker.remove())
  markerLayer = []
  hasMarkers.value = false

  props.stations.forEach((station) => {
    const coords = getCoords(station)
    if (!coords) return
    const marker = L.marker(coords, { opacity: station.id === props.selectedId ? 1 : 0.75 })
      .addTo(mapInstance)
      .bindTooltip(station.name ?? 'СТО')
    marker.__stationId = station.id
    marker.on('click', () => emit('select', station))
    markerLayer.push(marker)
    hasMarkers.value = true
  })

  if (hasMarkers.value) {
    const bounds = L.latLngBounds(markerLayer.map((marker) => marker.getLatLng()))
    mapInstance.fitBounds(bounds, { padding: [24, 24] })
  } else {
    mapInstance.setView(defaultCenter, 10)
  }
}

const highlightSelected = () => {
  markerLayer.forEach((marker: any) => {
    marker.setOpacity(marker.__stationId === props.selectedId ? 1 : 0.6)
  })
}

onMounted(async () => {
  if (!mapEl.value) return
  if (!process.client) return

  const [{ default: L }, iconRetina, icon, shadow] = await Promise.all([
    import('leaflet'),
    import('leaflet/dist/images/marker-icon-2x.png'),
    import('leaflet/dist/images/marker-icon.png'),
    import('leaflet/dist/images/marker-shadow.png')
  ])

  L.Icon.Default.mergeOptions({
    iconRetinaUrl: iconRetina.default ?? iconRetina,
    iconUrl: icon.default ?? icon,
    shadowUrl: shadow.default ?? shadow
  })

  mapInstance = L.map(mapEl.value).setView(defaultCenter, 10)
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap'
  }).addTo(mapInstance)

  mapReady.value = true
  resetMarkers(L)

  setTimeout(() => mapInstance?.invalidateSize(), 0)
})

watch(
  () => props.stations,
  async () => {
    if (!mapReady.value || !process.client) return
    const { default: L } = await import('leaflet')
    resetMarkers(L)
  },
  { deep: true }
)

watch(
  () => props.selectedId,
  async () => {
    if (!mapReady.value || !process.client) return
    const { default: L } = await import('leaflet')
    highlightSelected()
    const selected = props.stations.find((item) => item.id === props.selectedId)
    const coords = getCoords(selected)
    if (coords) mapInstance?.setView(coords, 13)
  }
)
</script>

