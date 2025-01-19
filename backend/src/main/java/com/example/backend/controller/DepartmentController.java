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

    @PatchMapping("/change-manager/{id}")
    public ResponseEntity<Integer> changeManager(@PathVariable int id,
                                                 @RequestBody String managerName) {
        return ResponseEntity.ok(departmentService.changeManager(managerName, id));
    }

    @PatchMapping("/change-name/{id}")
    public ResponseEntity<Integer> changeName(@PathVariable int id,
                                                 @RequestBody String departmentName) {
        return ResponseEntity.ok(departmentService.changeName(departmentName, id));
    }

    @PatchMapping("/change-location/{id}")
    public ResponseEntity<Integer> changeLocation(@PathVariable int id,
                                                 @RequestBody String locationName) {
        return ResponseEntity.ok(departmentService.changeLocation(locationName, id));
    }

}
