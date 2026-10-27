<template>
    <div>
        <h2>Room statistics</h2> 
        <div>
            <p v-if="loading">Loading statistics...</p>
            <p v-else-if="error">{{ error }}</p>
            <div v-else>
                <h3>Room count statistics</h3>
                <div>
                    <p>Total Rooms : </p> <p>{{ totalRooms }}</p>
                    <p>Available Rooms: </p> <p>{{ availableRooms }}</p>
                    <p>Booked Rooms: </p><p>{{ bookedRooms }}</p>
                </div>

                <h3>Statistics per room type</h3>
                <div>
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
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

import {ref, onMounted} from 'vue'
import axios from 'axios'

const loading = ref(true)
const error = ref('')
const availableRooms = ref(0)
const totalRooms = ref(0)
const bookedRooms = ref(0)
const rooms=ref([])
const deluxeAvailable = ref(0)
const deluxeBooked = ref(0)
const suiteAvailable = ref(0)
const suiteBooked = ref(0)

async function getStats(){
    try{
        const responseTotal = await axios.get('http://localhost:8082/api/rooms/count')
        totalRooms.value = responseTotal.data

        const responseAvailable = await axios.get('http://localhost:8082/api/rooms')
        rooms.value = responseAvailable.data
        availableRooms.value = rooms.value.filter(
            room=>room.status==="AVAILABLE"
        ).length

        bookedRooms.value = rooms.value.filter(
            room=>room.status==="BOOKED"
        ).length

        const s1 = await axios.get('http://localhost:8082/api/rooms/count/Deluxe/AVAILABLE')
        deluxeAvailable.value = s1.data
        const s2 = await axios.get('http://localhost:8082/api/rooms/count/Deluxe/BOOKED')
        deluxeBooked.value = s2.data
        const s3 = await axios.get('http://localhost:8082/api/rooms/count/Suite/AVAILABLE')
        suiteAvailable.value = s3.data
        const s4 = await axios.get('http://localhost:8082/api/rooms/count/Suite/BOOKED')
        suiteBooked.value = s4.data
    }
    catch(err){
        error.value="Could not load statistics"
    }
    finally{
        loading.value=false
    }
    
}
onMounted(getStats)
</script>

<style scoped>
</style>