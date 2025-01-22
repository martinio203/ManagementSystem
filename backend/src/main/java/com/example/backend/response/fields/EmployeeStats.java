package com.example.backend.response.fields;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EmployeeStats {
    private int countedEmployees;
    private BigDecimal avgSalary;
}
