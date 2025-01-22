package com.example.backend.service.impl;

import com.example.backend.repository.JobRepository;
import com.example.backend.request.AddNewJobRequest;
import com.example.backend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public List<Map<String, Object>> getAllJobs() {
        return jobRepository.findAllJobs();
    }

    @Override
    public Map<String, Object> getJobById(String id) {
        return jobRepository.findJobById(id);
    }

    @Override
    public int addJob(AddNewJobRequest request) {
        if (request.getJobTitle().isEmpty()) throw new IllegalArgumentException("Job title can not be empty");
        if (request.getMaxSalary() <= 0) throw new IllegalArgumentException("Max salary must be positive");
        if (request.getMinSalary() <= 0) throw new IllegalArgumentException("Min salary must be positive");
        return jobRepository.createNewJob(request);
    }

    @Override
    public int changeTitle(String id, String jobTitle) {
        if (jobTitle.isEmpty()) throw new IllegalArgumentException("Job title can not be empty");
        return jobRepository.changeTitle(id, jobTitle);
    }

    @Override
    public int changeMaxSalary(String id, int salary) {
        if (salary <= 0) throw new IllegalArgumentException("Salary must be positive");
        return jobRepository.changeMaxSalary(id, salary);
    }

    @Override
    public int changeMinSalary(String id, int salary) {
        if (salary <= 0) throw new IllegalArgumentException("Salary must be positive");
        return jobRepository.changeMinSalary(id, salary);
    }

    @Override
    public int countJobs() {
        return jobRepository.countJobs();
    }

    @Override
    public Map<String, Object> maxSalaryJob() {
        return jobRepository.maxSalaryJob();
    }

    @Override
    public Map<String, Object> minSalaryJob() {
        return jobRepository.minSalaryJob();
    }

    @Override
    public Map<String, Object> mostEmployeesJob() {
        return jobRepository.mostEmployeesJob();
    }

    @Override
    public int deleteJob(String id) {
        return jobRepository.deleteJob(id);
    }

}
