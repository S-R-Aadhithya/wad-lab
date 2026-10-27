
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const employees = ref([])
const empId = ref('')
const leaveType = ref('SICK')
const days = ref(1)
const reason = ref('')
const message = ref('')

onMounted(async () => {
  try {
    const res = await axios.get('http://localhost:8081/api/employees')
    employees.value = res.data
  } catch (error) {
    message.value = 'Failed to load employees'
  }
})

const applyLeave = async () => {
  try {
    await axios.post('http://localhost:8081/api/leaves', {
      employeeId: empId.value,
      leaveType: leaveType.value,
      days: days.value,
      reason: reason.value
    })

    message.value = 'Leave applied successfully!'
    reason.value = ''
    days.value = 1
  } catch (error) {
    message.value =
      error.response?.data?.message || 'Failed to apply for leave'
  }
}
</script>

<template>
  <form class="leave-form" @submit.prevent="applyLeave">
    <div>
      <label>Employee:</label>
      <select v-model="empId" required>
        <option value="" disabled>Select employee</option>
        <option
          v-for="emp in employees"
          :key="emp.employeeId"
          :value="emp.employeeId"
        >
          {{ emp.name }}
        </option>
      </select>
    </div>

    <div>
      <label>Leave Type:</label>
      <select v-model="leaveType" required>
        <option value="SICK">Sick</option>
        <option value="CASUAL">Casual</option>
        <option value="EARNED">Earned</option>
      </select>
    </div>

    <div>
      <label>Number of days:</label>
      <input
        type="number"
        v-model.number="days"
        min="1"
        required
      />
    </div>

    <div>
      <label>Reason:</label>
      <input v-model="reason" required />
    </div>

    <button type="submit">Apply</button>

    <p>{{ message }}</p>
  </form>
</template>

<style scoped>
.leave-form {
  display: flex;
  flex-direction: column;
  gap: 14px;
  max-width: 420px;
}

.leave-form div {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.leave-form label {
  font-weight: 600;
  color: #d1d5db;
}

.leave-form input,
.leave-form select {
  width: 100%;
}

.leave-form button {
  margin-top: 8px;
  align-self: flex-start;
}

.leave-form p {
  color: #93c5fd;
}
</style>
