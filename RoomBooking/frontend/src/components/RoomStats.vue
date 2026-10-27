<template>
  <div class="stats">
    <h2>Room Statistics</h2>

    <!-- Summary -->
    <div class="summary">
      <div class="card">
        <h3>Total Rooms</h3>
        <p>{{ totalRooms }}</p>
      </div>

      <div class="card">
        <h3>Available Rooms</h3>
        <p>{{ availableRooms }}</p>
      </div>

      <div class="card">
        <h3>Booked Rooms</h3>
        <p>{{ bookedRooms }}</p>
      </div>
    </div>

    <!-- Statistics by room type -->
    <h3>Statistics by Room Type</h3>

    <table>
      <thead>
        <tr>
          <th>Room Type</th>
          <th>Available</th>
          <th>Booked</th>
        </tr>
      </thead>

      <tbody>
        <tr>
          <td>Deluxe</td>
          <td>{{ deluxeAvailable }}</td>
          <td>{{ deluxeBooked }}</td>
        </tr>

        <tr>
          <td>Suite</td>
          <td>{{ suiteAvailable }}</td>
          <td>{{ suiteBooked }}</td>
        </tr>
      </tbody>
    </table>

    <button @click="getStats">Refresh Statistics</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const totalRooms = ref(0)
const availableRooms = ref(0)
const bookedRooms = ref(0)

const deluxeAvailable = ref(0)
const deluxeBooked = ref(0)
const suiteAvailable = ref(0)
const suiteBooked = ref(0)

async function getStats() {
  try {
    // Total rooms
    const total = await axios.get(
      'http://localhost:8082/api/rooms/count'
    )
    totalRooms.value = total.data

    // Available and booked rooms
    const response = await axios.get(
      'http://localhost:8082/api/rooms'
    )

    availableRooms.value = response.data.filter(
      room => room.status === 'AVAILABLE'
    ).length

    bookedRooms.value = response.data.filter(
      room => room.status === 'BOOKED'
    ).length

    // Deluxe room counts
    const d1 = await axios.get(
      'http://localhost:8082/api/rooms/count/Deluxe/AVAILABLE'
    )
    deluxeAvailable.value = d1.data

    const d2 = await axios.get(
      'http://localhost:8082/api/rooms/count/Deluxe/BOOKED'
    )
    deluxeBooked.value = d2.data

    // Suite room counts
    const s1 = await axios.get(
      'http://localhost:8082/api/rooms/count/Suite/AVAILABLE'
    )
    suiteAvailable.value = s1.data

    const s2 = await axios.get(
      'http://localhost:8082/api/rooms/count/Suite/BOOKED'
    )
    suiteBooked.value = s2.data

  } catch (error) {
    console.log('Error fetching room statistics:', error)
  }
}

onMounted(getStats)
</script>

<style scoped>
.stats {
  margin: 24px 0;
}

.summary {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}

.card {
  flex: 1;
  min-width: 120px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.card p {
  font-size: 24px;
  font-weight: bold;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin: 12px 0;
}

th,
td {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}

button {
  padding: 8px 12px;
  cursor: pointer;
}
</style>
