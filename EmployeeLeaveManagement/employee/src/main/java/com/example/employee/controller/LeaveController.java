package com.example.employee.controller;

import com.example.employee.dto.EmployeeLeaveDTO;
import com.example.employee.model.Leave;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class LeaveController {
    private final LeaveService leaveService;
    private final EmployeeService employeeService;

    @PostMapping
    public Leave addLeave(@RequestBody Leave leave) {
        return leaveService.addLeave(leave);
    }

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/employee/{employeeId}")
    public List<Leave> getLeaveByEmployeeId(
            @PathVariable String employeeId) {
        return leaveService.getLeaveByEmployeeId(employeeId);
    }

    @GetMapping("/{id}")
    public Leave getLeave(@PathVariable String id) {
        return leaveService.getLeave(id);
    }

    // Delete a pending leave request
    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable String id) {
        leaveService.deleteLeave(id);
    }


    @GetMapping("/type/{leaveType}")
    public List<EmployeeLeaveDTO> getEmployeesByLeaveType(
            @PathVariable String leaveType) {
        return leaveService.getEmployeesByLeaveType(leaveType);
    }


}
