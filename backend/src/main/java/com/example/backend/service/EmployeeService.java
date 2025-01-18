package com.example.backend.service;

import com.example.backend.request.AddEmployeeRequest;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Map<String, Object> getEmployeeById(int id);
    List<Map<String, Object>> findAllEmployees();
    int addEmployee(AddEmployeeRequest request);
    String changeEmployeeDetails(int id, Map<String, Object> toChange);
    int countEmployees();
    Map<String, Object> employeeDetails(int id);
//    int changeJob(int id, String newJobName);
//    int changeDeparment(int id, String newDepartment);
}
