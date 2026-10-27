
<template>
  <div class="container">
    <h1>Hotel Room Booking</h1>
    <RoomStats :key="refreshKey" />
    <RoomList
      :key="refreshKey"
      @select-room="selectRoom"
    />

    <hr />

    <BookingForm
      :room-id="selectedRoomId"
      @booked="refreshRooms"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import RoomStats from './components/roomstats2.vue'
import RoomList from './components/RoomList.vue'
import BookingForm from './components/BookingForm.vue'

const selectedRoomId = ref('')
const refreshKey = ref(0)

function selectRoom(room) {
  selectedRoomId.value = room.roomId
}

function refreshRooms() {
  refreshKey.value++
}
</script>

<style>
.container {
  max-width: 700px;
  margin: 30px auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}
</style>
