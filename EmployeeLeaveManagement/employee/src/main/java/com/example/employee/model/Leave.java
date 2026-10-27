package com.example.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "leaves")
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    private String leaveId;
    private String employeeId;
    private String reason;
    private String leaveType;
    private int days;
    private String status;
}
