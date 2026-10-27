<template>
    <div>
        <h2> Book a room </h2>

        <p v-if="roomId">
            Selected room id: {{ roomId }}
        </p>
        <p v-else>Please select an available room first.</p>

        <form @submit.prevent="bookRoom">
            <label> Customer Name</label>
            <input v-model="customerName" required />

            <label> Phone Number</label>
            <input v-model="phone" required />

            <label> Room ID</label>
            <input :value="roomId" readonly required />

            <button type="submit" :disabled="!roomId">
                Confirm Booking
            </button>

        </form>

        <p v-if="message ">{{ message }}</p>

    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'

import axios from 'axios'
const props = defineProps({
    roomId: String
})

const emit = defineEmits(['booked'])

const customerName= ref('')
const phone= ref('')
const message = ref('')

async function bookRoom(){
    message.value=''
    try{
        const response = await axios.post(
            'http://localhost:8082/api/bookings',
            {
                customerName: customerName.value,
                phone: phone.value,
                roomId:props.roomId
            }
        )

        message.value= 'Booking Successful'
        customerName.value=''
        phone.value = ''
        emit('booked')
    }
    catch(err){
        message.value=err.response?.data?.detail || err.response?.data?.message ||
      'Booking failed. Please check room availability.'
    }
}
</script>

<style scoped>
label {
  display: block;
  margin-top: 12px;
}

input {
  display: block;
  width: 100%;
  box-sizing: border-box;
  padding: 8px;
  margin-top: 4px;
  margin-bottom: 8px;
}

button {
  padding: 10px 14px;
  margin-top: 8px;
}
</style>