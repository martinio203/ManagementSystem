package com.example.backend.repository;

import com.example.backend.request.AddNewJobRequest;

import java.util.List;
import java.util.Map;

public interface JobRepository {
    List<Map<String, Object>> findAllJobs();
    Map<String, Object> findJobById(String id);
    int createNewJob(AddNewJobRequest request);
    int changeTitle(String id, String title);
    int changeMinSalary(String id, int salary);
    int changeMaxSalary(String id, int salary);
}
