package com.example.backend.repository;


import com.example.backend.request.AddEmployeeRequest;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository{
    List<Map<String, Object>> displayAllEmployees();
    Map<String, Object> findEmployeeById(int id);
    Map<String, Object> employeeDetails(int id);
    boolean employeeExist(int id);
    int addEmployee(AddEmployeeRequest request);
    int changeSalary(int id, int newSalary);
    int changeFirstName(int id, String newFirstName);
    int changeEmail(int id, String newEmail);
    int changeNumber(int id, String newNumber);
    int changeLastName(int id, String newLastName);
    int changeDepartment(int id, String newDepartment);
    int changeManager(int id, String managerFirstName, String managerLastName);
    int countEmployees();
}
