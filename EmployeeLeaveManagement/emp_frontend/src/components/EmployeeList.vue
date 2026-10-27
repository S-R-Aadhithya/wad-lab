
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const loading = ref(true)
const error = ref('')
const employees = ref([])

async function loadEmployees() {
  try {
    const res = await axios.get('http://localhost:8081/api/employees')
    employees.value = res.data
  } catch (err) {
    error.value = 'Failed to load employee data'
  } finally {
    loading.value = false
  }
}

onMounted(loadEmployees)
</script>

<template>
  <p v-if="loading">Loading employees...</p>
  <p v-else-if="error">{{ error }}</p>

  <table v-else>
    <thead>
      <tr>
        <th>Employee ID</th>
        <th>Name</th>
        <th>Department</th>
        <th>Sick Leave</th>
        <th>Casual Leave</th>
        <th>Earned Leave</th>
      </tr>
    </thead>

    <tbody>
      <tr v-for="emp in employees" :key="emp.employeeId">
        <td>{{ emp.employeeId }}</td>
        <td>{{ emp.name }}</td>
        <td>{{ emp.department }}</td>
        <td>{{ emp.sickLeaveBalance }}</td>
        <td>{{ emp.casualLeaveBalance }}</td>
        <td>{{ emp.earnedLeaveBalance }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  text-align: left;
}

th, td {
  padding: 12px;
  border-bottom: 1px solid #374151;
}

th {
  color: #93c5fd;
  background: #111827;
}

tbody tr:hover {
  background: #273449;
}
</style>
