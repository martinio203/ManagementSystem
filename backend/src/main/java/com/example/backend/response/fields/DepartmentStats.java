package com.example.backend.response.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class DepartmentStats {
    private Map<String, Object> mostEmployees;
    private int countedDepartments;
}
