```vue
<template>
  <div>
    <h2>Available Rooms</h2>

    <p v-if="loading">Loading rooms...</p>
    <p v-else-if="error">{{ error }}</p>

    <div v-else>
      <div v-for="room in rooms" :key="room.roomId" class="room">
        <h3>{{ room.roomType }} Room</h3>
        <p>Price: ₹{{ room.price }}</p>
        <p>Status: {{ room.status }}</p>

        <button
          v-if="room.status === 'AVAILABLE'"
          @click="$emit('select-room', room)"
        >
          Select Room
        </button>

        <button v-else disabled>
          Already Booked
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

defineProps({
  refreshKey: Number
})

defineEmits(['select-room'])

const rooms = ref([])
const loading = ref(true)
const error = ref('')

async function getRooms() {
  loading.value = true
  error.value = ''

  try {
    const response = await axios.get(
      'http://localhost:8082/api/rooms'
    )
    rooms.value = response.data
  } catch (err) {
    error.value = 'Could not load rooms.'
  } finally {
    loading.value = false
  }
}

onMounted(getRooms)
</script>

<style scoped>
.room {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 12px;
  margin-bottom: 12px;
}

button {
  padding: 8px 12px;
  cursor: pointer;
}

button:disabled {
  cursor: not-allowed;
}
</style>
