package com.example.backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddNewJobRequest {
    private String jobId;
    private String jobTitle;
    private int minSalary;
    private int maxSalary;
}
