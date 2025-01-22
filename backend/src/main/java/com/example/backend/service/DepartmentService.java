package com.example.backend.service;

import com.example.backend.request.AddNewDepartmentRequest;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Map<String, Object>> getAllDepartments();
    Map<String, Object> findDepartmentById(int id);
    int createNewDepartment(AddNewDepartmentRequest request);
    Map<String, Object> departmentDetails(int id);
    int changeManager(String manager, int id);
    int changeName(String name, int id);
    int changeLocation(String locationName, int id);

    int countDepartments();

    Map<String, Object> highestEmployees();

    int deleteDepartment(int id);
}
