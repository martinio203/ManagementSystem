package com.example.backend.controller;

import com.example.backend.request.AddEmployeeRequest;
import com.example.backend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeDetails(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addEmployee(@RequestBody AddEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.addEmployee(request));
    }

    @PatchMapping("/change-details/{id}")
    public ResponseEntity<String> changeEmployeeDetails(@PathVariable int id,
                                                        @RequestBody Map<String, Object> details) {
        return ResponseEntity.ok(employeeService.changeEmployeeDetails(id, details));
    }
}
