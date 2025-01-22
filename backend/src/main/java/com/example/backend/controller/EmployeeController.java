package com.example.backend.controller;

import com.example.backend.request.AddEmployeeRequest;
import com.example.backend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeDetails(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.employeeDetails(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countEmployees() {
        return ResponseEntity.ok(employeeService.countEmployees());
    }

    @GetMapping("/avg-salary")
    public ResponseEntity<BigDecimal> getAvgSalary() {
        return ResponseEntity.ok(employeeService.avgSalary());
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addEmployee(@RequestBody AddEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.addEmployee(request));
    }

    @PatchMapping("/change-salary/{id}")
    public ResponseEntity<Integer> changeSalary(@PathVariable int id,
                                                @RequestBody int salary) {
        return ResponseEntity.ok(employeeService.changeSalary(id, salary));
    }

    @PatchMapping("/change-hire-date/{id}")
    public ResponseEntity<Integer> changeHireDate(@PathVariable int id,
                                                   @RequestBody String date) {
        return ResponseEntity.ok(employeeService.changeHireDate(id, date));
    }

    @PatchMapping("/change-name/{id}")
    public ResponseEntity<Integer> changeName(@PathVariable int id,
                                              @RequestBody String name) {
        return ResponseEntity.ok(employeeService.changeEmployeeName(id, name));
    }


    @PatchMapping("/change-first-name/{id}")
    public ResponseEntity<Integer> changeFirstName(@PathVariable int id,
                                                   @RequestBody String firstName) {
        return ResponseEntity.ok(employeeService.changeFirstName(id, firstName));
    }

    @PatchMapping("/change-last-name/{id}")
    public ResponseEntity<Integer> changeLastName(@PathVariable int id,
                                                  @RequestBody String lastName) {
        return ResponseEntity.ok(employeeService.changeLastName(id, lastName));
    }

    @PatchMapping("/change-email/{id}")
    public ResponseEntity<Integer> changeEmail(@PathVariable int id,
                                                  @RequestBody String email) {
        return ResponseEntity.ok(employeeService.changeEmail(id, email));
    }

    @PatchMapping("/change-number/{id}")
    public ResponseEntity<Integer> changeNumber(@PathVariable int id,
                                                  @RequestBody String number) {
        return ResponseEntity.ok(employeeService.changeNumber(id, number));
    }

    @PatchMapping("/change-department/{id}")
    public ResponseEntity<Integer> changeDepartment(@PathVariable int id,
                                                  @RequestBody String department) {
        return ResponseEntity.ok(employeeService.changeDepartment(id, department));
    }

    @PatchMapping("/change-manager/{id}")
    public ResponseEntity<Integer> changeManager(@PathVariable int id,
                                                  @RequestBody String name) {
        return ResponseEntity.ok(employeeService.changeManager(id, name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteEmployee(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
}

