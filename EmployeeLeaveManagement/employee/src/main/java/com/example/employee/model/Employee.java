package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private String employeeId;
    private String name;
    private double salary;
    private String department;
    private int sickLeaveBalance;
    private int casualLeaveBalance;
    private int earnedLeaveBalance;

}
