package com.example.employee.repository;

import com.example.employee.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LeaveRepository extends MongoRepository<Leave,String> {
    public List<Leave>findByEmployeeId(String employeeId);
    List<Leave> findByLeaveTypeIgnoreCase(String leaveType);
}
