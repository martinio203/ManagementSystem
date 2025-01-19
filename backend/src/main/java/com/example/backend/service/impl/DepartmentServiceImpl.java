package com.example.backend.service.impl;

import com.example.backend.repository.DepartmentRepository;
import com.example.backend.request.AddNewDepartmentRequest;
import com.example.backend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Map<String, Object>> getAllDepartments() {
        return departmentRepository.displayAllDepartments();
    }

    @Override
    public Map<String, Object> findDepartmentById(int id) {
        return departmentRepository.findDepartmentById(id);
    }

    @Override
    public int createNewDepartment(AddNewDepartmentRequest request) {
        if (departmentRepository.departmentExist(request.getDepartmentName())) {
            throw new IllegalArgumentException("Department already exist");
        }

        return departmentRepository.createNewDepartment(request);
    }

    @Override
    public Map<String, Object> departmentDetails(int id) {
        return departmentRepository.departmentDetails(id);
    }

    @Override
    public int changeManager(String manager, int id) {
        if (manager.isEmpty()) throw new IllegalArgumentException("First and last name can no be empty");
        String[] managerName = manager.split(" ");
        return departmentRepository.changeManger(managerName[0], managerName[1], id);
    }

    @Override
    public int changeName(String name, int id) {
        if (name.isEmpty()) throw new IllegalArgumentException("Department name can not be empty");
        return departmentRepository.changeDepartmentName(name, id);
    }

    @Override
    public int changeLocation(String locationName, int id) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name can not be empty");
        return departmentRepository.changeLocationName(locationName, id);
    }

}
