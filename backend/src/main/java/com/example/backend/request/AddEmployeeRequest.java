package com.example.backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AddEmployeeRequest {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private String jobId;
    private int salary;
    private int managerId;
    private int departmentId;
}
