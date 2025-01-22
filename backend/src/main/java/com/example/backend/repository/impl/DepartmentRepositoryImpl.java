package com.example.backend.repository.impl;

import com.example.backend.repository.DepartmentRepository;
import com.example.backend.request.AddNewDepartmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final JdbcTemplate jdbc;

    @Override
    public List<Map<String, Object>> displayAllDepartments() {
        String sql =  "SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME AS DEPARTMENT_NAME," +
                "L.CITY AS CITY," +
                "C.COUNTRY_NAME AS COUNTRY_NAME " +
                "FROM " +
                "DEPARTMENTS D " +
                "LEFT JOIN " +
                "LOCATIONS L ON D.LOCATION_ID = L.LOCATION_ID " +
                "LEFT JOIN " +
                "COUNTRIES C ON L.COUNTRY_ID = C.COUNTRY_ID ";

        return jdbc.queryForList(sql);
    }

    @Override
    public Map<String, Object> departmentDetails(int id) {
        String sql = "SELECT " +
                "D.DEPARTMENT_NAME AS DEPARTMENT_NAME," +
                "E.FIRST_NAME AS MANAGER_FIRST_NAME," +
                "E.LAST_NAME AS MANAGER_LAST_NAME," +
                "L.CITY AS CITY," +
                "C.COUNTRY_NAME AS COUNTRY_NAME," +
                "R.REGION_NAME AS REGION_NAME " +
                "FROM " +
                "DEPARTMENTS D " +
                "LEFT JOIN " +
                "EMPLOYEES E ON D.MANAGER_ID = E.EMPLOYEE_ID " +
                "LEFT JOIN " +
                "LOCATIONS L ON D.LOCATION_ID = L.LOCATION_ID " +
                "LEFT JOIN " +
                "COUNTRIES C ON L.COUNTRY_ID = C.COUNTRY_ID " +
                "LEFT JOIN " +
                "REGIONS R ON C.REGION_ID = R.REGION_ID "+
                "WHERE D.DEPARTMENT_ID = ?";

        return jdbc.queryForMap(sql, id);
    }

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
    public boolean departmentExist(String departmentName) {
        String sql = "SELECT COUNT(*) FROM DEPARTMENTS WHERE DEPARTMENT_NAME = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, departmentName);
        return count != null && count == 1;
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

    @Override
    public int changeDepartmentName(String department, int id) {
        String sql = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME = ? WHERE DEPARTMENT_ID = ?";
        return jdbc.update(sql, department, id);
    }

    @Override
    public int changeManger(String managerFirstName, String managerLastName, int id) {
        String sql = "UPDATE DEPARTMENTS SET MANAGER_ID = (" +
                "SELECT EMPLOYEE_ID FROM EMPLOYEES WHERE FIRST_NAME = ? AND LAST_NAME = ?)" +
                " WHERE DEPARTMENT_ID = ?";

        return jdbc.update(sql, managerFirstName, managerLastName, id);
    }

    @Override
    public int changeLocationName(String location, int id) {
        String sql = "UPDATE DEPARTMENTS SET LOCATION_ID = " +
                "(SELECT LOCATION_ID FROM LOCATIONS WHERE CITY = ?)" +
                " WHERE DEPARTMENT_ID = ?";
        return jdbc.update(sql, location, id);
    }

    @Override
    public int countDepartments() {
        String sql = "SELECT COUNT(*) FROM DEPARTMENTS";
        Integer count = jdbc.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public Map<String, Object> mostEmployees() {
        String sql = "SELECT department_name, employees_count " +
                "FROM (" +
                    "SELECT d.department_name, COUNT(*) AS employees_count " +
                    "FROM employees e " +
                    "JOIN departments d ON e.department_id = d.department_id " +
                    "GROUP BY d.department_name " +
                    "ORDER BY employees_count DESC" +
                    ") " +
                "WHERE ROWNUM = 1";
        return jdbc.queryForMap(sql);
    }

    @Override
    public int deleteDepartment(int id) {
        String sql = "DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";
        return jdbc.update(sql, id);
    }
}
