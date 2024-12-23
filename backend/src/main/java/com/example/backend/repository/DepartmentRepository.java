package com.example.backend.repository;

import com.example.backend.request.AddNewDepartmentRequest;

import java.util.Map;

public interface DepartmentRepository {
    Map<String, Object> findDepartmentById(int id);
    Map<String, Object> findDepartmentByName(String departmentName);
    int createNewDepartment(AddNewDepartmentRequest request);
}
