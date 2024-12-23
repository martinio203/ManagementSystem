package com.example.backend.repository.impl;

import com.example.backend.repository.DepartmentRepository;
import com.example.backend.request.AddNewDepartmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final JdbcTemplate jdbc;

    @Override
    public Map<String, Object> findDepartmentById(int id) {
        String sql = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";
        return jdbc.queryForMap(sql, id);
    }

    @Override
    public Map<String, Object> findDepartmentByName(String departmentName) {
        String sql = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_NAME = ?";
        return jdbc.queryForMap(sql, departmentName);
    }

    @Override
    public int createNewDepartment(AddNewDepartmentRequest request) {
        String sql = "INSERT INTO DEPARTMENTS(DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)" +
                "VALUES(?, ?, ?, ?)";
        return jdbc.update(sql,
                request.getDepartmentId(),
                request.getDepartmentName(),
                request.getManagerId(),
                request.getLocationId()
        );
    }
}
