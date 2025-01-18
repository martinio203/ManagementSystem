package com.example.backend.service;

import com.example.backend.request.AddNewDepartmentRequest;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Map<String, Object>> getAllDepartments();
    Map<String, Object> findDepartmentById(int id);
    int createNewDepartment(AddNewDepartmentRequest request);
    Map<String, Object> departmentDetails(int id);
    String changeDepartmentsDetails(int id, Map<String, Object> toChange);
}
