package com.example.backend.service;


import com.example.backend.request.AddNewJobRequest;

import java.util.List;
import java.util.Map;

public interface JobService {

    List<Map<String, Object>> getAllJobs();
    Map<String, Object> getJobById(String id);
    int addJob(AddNewJobRequest request);
    int changeTitle(String id, String jobTitle);
    int changeMaxSalary(String id, int salary);
    int changeMinSalary(String id, int salary);

    int countJobs();

    Map<String, Object> maxSalaryJob();

    Map<String, Object> minSalaryJob();

    Map<String, Object> mostEmployeesJob();

    int deleteJob(String id);
}
