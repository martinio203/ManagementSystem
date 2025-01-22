package com.example.backend.controller;

import com.example.backend.request.AddNewJobRequest;
import com.example.backend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {
    private final JobService jobService;


    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Map<String, Object>> getJobById(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countJobs() {
        return ResponseEntity.ok(jobService.countJobs());
    }

    @GetMapping("/max-salary")
    public ResponseEntity<Map<String, Object>> maxSalaryJob() {
        return ResponseEntity.ok(jobService.maxSalaryJob());
    }

    @GetMapping("/min-salary")
    public ResponseEntity<Map<String, Object>> minSalaryJob() {
        return ResponseEntity.ok(jobService.minSalaryJob());
    }

    @GetMapping("/most-employees-job")
    public ResponseEntity<Map<String, Object>> mostEmployees() {
        return ResponseEntity.ok(jobService.mostEmployeesJob());
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> createNewJob(@RequestBody AddNewJobRequest request) {
        return ResponseEntity.ok(jobService.addJob(request));
    }

    @PatchMapping("/change-title/{id}")
    public ResponseEntity<Integer> changeTitle(@PathVariable String id,
                                               @RequestBody String jobTitle) {
        return ResponseEntity.ok(jobService.changeTitle(id, jobTitle));
    }

    @PatchMapping("/change-max-salary/{id}")
    public ResponseEntity<Integer> changeMaxSalary(@PathVariable String id,
                                                   @RequestBody int salary) {
        return ResponseEntity.ok(jobService.changeMaxSalary(id, salary));
    }

    @PatchMapping("/change-min-salary/{id}")
    public ResponseEntity<Integer> changeMinSalary(@PathVariable String id,
                                                   @RequestBody int salary) {
        return ResponseEntity.ok(jobService.changeMinSalary(id, salary));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteJob(@PathVariable String id) {
        return ResponseEntity.ok(jobService.deleteJob(id));
    }

}
