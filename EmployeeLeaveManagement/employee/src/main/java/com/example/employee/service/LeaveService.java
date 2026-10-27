package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.Leave;
import com.example.employee.dto.EmployeeLeaveDTO;
import java.util.ArrayList;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.LeaveRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class LeaveService {
    private final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;

    public Leave addLeave(Leave leave){
        Employee emp = employeeRepository.findById(leave.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Employee not found"
                ));
        String type = leave.getLeaveType();
        if (type == null ||
                (!type.equalsIgnoreCase("SICK")
                        && !type.equalsIgnoreCase("CASUAL")
                        && !type.equalsIgnoreCase("EARNED"))) {

            throw new ResponseStatusException(
                    BAD_REQUEST, "Type must be either CASUAL/SICK/EARNED"
            );
        }

        if(leave.getDays()<=0){
            throw new ResponseStatusException(BAD_REQUEST, "Leave count must be greater than 0.");
        }
        if(type.equalsIgnoreCase("SICK") ){
            if(emp.getSickLeaveBalance()< leave.getDays()){
                throw new ResponseStatusException(CONFLICT,"Leave request greater than available count");
            }
            else{
                emp.setSickLeaveBalance(emp.getSickLeaveBalance()-leave.getDays());
            }

        }
        else if(type.equalsIgnoreCase("CASUAL") ){
            if(emp.getCasualLeaveBalance()< leave.getDays()){
                throw new ResponseStatusException(CONFLICT,"Leave request greater than available count");
            }
            else{
                emp.setCasualLeaveBalance(emp.getCasualLeaveBalance()-leave.getDays());
            }

        }

        else if(type.equalsIgnoreCase("EARNED") ){
            if(emp.getEarnedLeaveBalance()< leave.getDays()){
                throw new ResponseStatusException(CONFLICT,"Leave request greater than available count");
            }
            else{
                emp.setEarnedLeaveBalance(emp.getEarnedLeaveBalance()-leave.getDays());
            }

        }
        leave.setStatus("PENDING");
        employeeRepository.save(emp);
        return leaveRepository.save(leave);
    }

    public List<Leave> getAllLeaves(){
        return leaveRepository.findAll();
    }

    public List<Leave> getLeaveByEmployeeId(String employeeId){
        return leaveRepository.findByEmployeeId(employeeId);
    }
    public Leave getLeave(String id){
        return leaveRepository.findById(id).orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Leave not found"));
    }

    public void deleteLeave(String leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Leave not found"
                ));

        if (!"PENDING".equalsIgnoreCase(leave.getStatus())) {
            throw new ResponseStatusException(
                    CONFLICT, "Only pending leaves can be deleted"
            );
        }

        Employee emp = employeeRepository.findById(leave.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND, "Employee not found"
                ));

        String type = leave.getLeaveType();
        int days = leave.getDays();

        if (type.equalsIgnoreCase("SICK")) {
            emp.setSickLeaveBalance(emp.getSickLeaveBalance() + days);
        } else if (type.equalsIgnoreCase("CASUAL")) {
            emp.setCasualLeaveBalance(emp.getCasualLeaveBalance() + days);
        } else if (type.equalsIgnoreCase("EARNED")) {
            emp.setEarnedLeaveBalance(emp.getEarnedLeaveBalance() + days);
        } else {
            throw new ResponseStatusException(
                    BAD_REQUEST, "Invalid leave type"
            );
        }

        employeeRepository.save(emp);
        leaveRepository.delete(leave);
    }

    public List<EmployeeLeaveDTO> getEmployeesByLeaveType(String type){
        return leaveRepository.findAll().stream()
                .filter(leave -> leave.getLeaveType().equalsIgnoreCase(type))
                .map(leave -> {
            Employee emp = employeeRepository.findById(leave.getEmployeeId())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Employee not found"));
            return new EmployeeLeaveDTO(emp.getEmployeeId(), emp.getName());
                                    }).distinct().toList();
    }


}
