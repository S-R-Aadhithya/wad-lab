
<script setup>
import EmployeeList from './components/EmployeeList.vue'
import LeaveForm from './components/LeaveForm.vue'
</script>

<template>
  <main class="container">
    <h1>Employee Leave Management</h1>

    <section class="card">
      <h2>Employee Details</h2>
      <EmployeeList />
    </section>

    <section class="card">
      <h2>Apply for Leave</h2>
      <LeaveForm />
    </section>
  </main>
</template>

<style>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  background: #111827;
  color: #e5e7eb;
  font-family: Arial, sans-serif;
}

.container {
  max-width: 1050px;
  margin: 40px auto;
  padding: 0 20px;
}

h1 {
  text-align: center;
  margin-bottom: 30px;
}

h2 {
  color: #93c5fd;
  margin-top: 0;
}

.card {
  background: #1f2937;
  padding: 24px;
  margin-bottom: 24px;
  border: 1px solid #374151;
  border-radius: 10px;
}

button {
  background: #3b82f6;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 6px;
  cursor: pointer;
}

button:hover {
  background: #2563eb;
}

input, select {
  background: #111827;
  color: #e5e7eb;
  border: 1px solid #4b5563;
  padding: 8px;
  border-radius: 5px;
}

input:focus, select:focus {
  outline: 2px solid #60a5fa;
  border-color: transparent;
}
</style>
