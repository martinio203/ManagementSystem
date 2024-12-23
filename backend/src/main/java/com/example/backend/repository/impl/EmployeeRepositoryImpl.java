package com.example.backend.repository.impl;

import com.example.backend.repository.EmployeeRepository;
import com.example.backend.request.AddEmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> findAllEmployees() {
        String sql = "SELECT FIRST_NAME, LAST_NAME, EMAIL FROM EMPLOYEES";
        return jdbc.queryForList(sql);
    }

    @Override
    public Map<String, Object> findEmployeeById(int id) {
        String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
        return jdbc.queryForMap(sql, id);
    }

    @Override
    public boolean employeeExist(int id) {
        String sql = "SELECT COUNT(*) FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count == 1;
    }

    @Override
    public int addEmployee(AddEmployeeRequest request) {
        String sql = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                request.getEmployeeId(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getHireDate(),
                request.getJobId(),
                request.getSalary(),
                request.getManagerId(),
                request.getDepartmentId()
        );
    }

    @Override
    public int changeSalary(int id, int newSalary) {
        String sql = "UPDATE EMPLOYEES SET SALARY = ? WHERE EMPLOYEE_ID = ?";
        return jdbc.update(sql, newSalary, id);
    }

    @Override
    public int changeFirstName(int id, String newFirstName) {
        String sql = "UPDATE EMPLOYEES SET FIRST_NAME = ? WHERE EMPLOYEE_ID = ?";
        return jdbc.update(sql, newFirstName, id);
    }

    @Override
    public int changeEmail(int id, String newEmail) {
        String sql = "UPDATE EMPLOYEES SET EMAIL = ? WHERE EMPLOYEE_ID = ?";
        return jdbc.update(sql, newEmail, id);
    }

    @Override
    public int changeNumber(int id, String newNumber) {
        String sql = "UPDATE EMPLOYEES SET PHONE_NUMBER = ? WHERE EMPLOYEE_ID = ?";
        return jdbc.update(sql, newNumber, id);
    }

    @Override
    public int changeLastName(int id, String newLastName) {
        String sql = "UPDATE EMPLOYEES SET LAST_NAME = ? WHERE EMPLOYEE_ID = ?";
        return jdbc.update(sql, newLastName, id);
    }

    @Override
    public int changeDepartment(int id, String newDepartment) {
        String sql = "UPDATE EMPLOYEES SET DEPARTMENT_ID = (" +
                "SELECT DEPARTMENT_ID FROM DEPARTMENTS WHERE DEPARTMENT_NAME = ?)" +
                "WHERE EMPLOYEE_ID = ?";

        return jdbc.update(sql, newDepartment, id);
    }

    @Override
    public int changeManager(int id, String managerFirstName, String managerLastName) {
        String sql = "UPDATE EMPLOYEES SET MANAGER_ID = (" +
                "SELECT EMPLOYEE_ID FROM EMPLOYEES WHERE FIRST_NAME = ? AND LAST_NAME = ?)" +
                "WHERE EMPLOYEE_ID = ?";

        return jdbc.update(sql, managerFirstName, managerLastName, id);
    }


}
