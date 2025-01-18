package com.example.backend.controller;

import com.example.backend.request.AddNewDepartmentRequest;
import com.example.backend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Map<String, Object>> getDepartmentById(@PathVariable int id) {
        return ResponseEntity.ok(departmentService.findDepartmentById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> createDepartment(@RequestBody AddNewDepartmentRequest request) {
        return ResponseEntity.ok(departmentService.createNewDepartment(request));
    }

    @PatchMapping("/change-details/{id}")
    public ResponseEntity<String> changeDepartmentDetails(@PathVariable int id,
                                                          @RequestBody Map<String, Object> details) {
        return ResponseEntity.ok(departmentService.changeDepartmentsDetails(id, details));
    }

}
