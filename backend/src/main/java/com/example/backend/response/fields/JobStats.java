package com.example.backend.response.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class JobStats {
    private int countedJobs;
    private Map<String, Object> minSalary;
    private Map<String, Object> maxSalary;
    private Map<String, Object> mostEmployees;
}
