package com.example.backend.repository;

import com.example.backend.request.AddNewDepartmentRequest;

import java.util.List;
import java.util.Map;

public interface DepartmentRepository {
    List<Map<String, Object>> displayAllDepartments();
    Map<String, Object> departmentDetails(int id);
    Map<String, Object> findDepartmentById(int id);
    Map<String, Object> findDepartmentByName(String departmentName);
    boolean departmentExist(String departmentName);
    int createNewDepartment(AddNewDepartmentRequest request);
    int changeDepartmentName(String department, int id);
    int changeManger(String managerFirstName, String managerLastName, int id);
    int changeLocationName(String location, int id);

    int countDepartments();

    Map<String, Object> mostEmployees();

    int deleteDepartment(int id);
}
