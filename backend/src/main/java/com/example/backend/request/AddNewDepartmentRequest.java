package com.example.backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddNewDepartmentRequest {
    private int departmentId;
    private String departmentName;
    private int managerId;
    private int locationId;
}
