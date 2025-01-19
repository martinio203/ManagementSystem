package com.example.backend.service;

import com.example.backend.request.AddEmployeeRequest;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Map<String, Object> getEmployeeById(int id);
    List<Map<String, Object>> findAllEmployees();
    int addEmployee(AddEmployeeRequest request);
    int countEmployees();
    Map<String, Object> employeeDetails(int id);
    int changeSalary(int id, int newSalary);
    int changeFirstName(int id, String newFirstName);
    int changeLastName(int id, String newLastName);
    int changeEmail(int id, String newEmail);
    int changeNumber(int id, String newNumber);
    int changeDepartment(int id, String newDepartment);
    int changeManager(int id, String name);
}
