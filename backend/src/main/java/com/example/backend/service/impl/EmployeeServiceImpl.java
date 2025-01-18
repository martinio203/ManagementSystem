package com.example.backend.service.impl;

import com.example.backend.repository.DepartmentRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.request.AddEmployeeRequest;
import com.example.backend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Map<String, Object> getEmployeeById(int id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public List<Map<String, Object>> findAllEmployees() {
        return employeeRepository.displayAllEmployees();
    }

    @Override
    public int addEmployee(AddEmployeeRequest request) {
        if (employeeRepository.employeeExist(request.getEmployeeId())) {
            throw new IllegalArgumentException("User already exist");
        }

        return employeeRepository.addEmployee(request);
    }

    @Override
    public String changeEmployeeDetails(int id, Map<String, Object> toChange) {
        final int[] rowsAffected = {0};
        toChange.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    rowsAffected[0] += changeFirstName(id, (String) value);
                    break;
                case "lastName":
                    rowsAffected[0] += changeLastName(id, (String) value);
                    break;
                case "email":
                    rowsAffected[0] +=  changeEmail(id, (String) value);
                    break;
                case "phoneNumber":
                    rowsAffected[0] += changeNumber(id, (String) value);
                    break;
                case "salary":
                    rowsAffected[0] += changeSalary(id, (int) value);
                    break;
                case "manager":
                    String[] managerName = ((String) value).split(" ");
                    rowsAffected[0] += changeManager(id, managerName[0], managerName[1]);
                    break;
                case "department":
                    rowsAffected[0] += changeDepartment(id, (String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return "Successfully changed " + rowsAffected[0] + " rows out of " + toChange.size() + " requested changes";
    }

    private int changeSalary(int id, int newSalary) {
        if (!(newSalary >= 0)) throw new IllegalArgumentException("New salary must be positive");
        return employeeRepository.changeSalary(id, newSalary);
    }

    private int changeFirstName(int id, String newFirstName) {
        if (newFirstName.isEmpty()) throw new IllegalArgumentException("New first name can not be empty");
        return employeeRepository.changeFirstName(id, newFirstName);
    }

    private int changeLastName(int id, String newLastName) {
        if (newLastName.isEmpty()) throw new IllegalArgumentException("New last name can not be empty");
        return employeeRepository.changeLastName(id, newLastName);
    }

    private int changeEmail(int id, String newEmail) {
        if (newEmail.isEmpty()) throw new IllegalArgumentException("New email can not be empty");
        return employeeRepository.changeEmail(id, newEmail);
    }

    private int changeNumber(int id, String newNumber) {
        if (newNumber.isEmpty()) throw new IllegalArgumentException("New number can not be empty");
        return employeeRepository.changeNumber(id, newNumber);
    }

    private int changeDepartment(int id, String newDepartment) {
        if (newDepartment.isEmpty()) throw new IllegalArgumentException("Department name can not be empty");
        if (!departmentRepository.departmentExist(newDepartment)) {
            throw new IllegalArgumentException("Departmet " + newDepartment + " does not exist");
        }
        return employeeRepository.changeDepartment(id, newDepartment);
    }

    private int changeManager(int id, String firstName, String lastName) {
        if (firstName.isEmpty() && lastName.isEmpty()) throw new IllegalArgumentException("First and last name can not be empty");
        return employeeRepository.changeManager(id, firstName, lastName);
    }

    @Override
    public int countEmployees() {
        return employeeRepository.countEmployees();
    }

    @Override
    public Map<String, Object> employeeDetails(int id) {
        return employeeRepository.employeeDetails(id);
    }

}

