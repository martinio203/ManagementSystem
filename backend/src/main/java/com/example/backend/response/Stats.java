package com.example.backend.response;

import com.example.backend.response.fields.CountryStats;
import com.example.backend.response.fields.DepartmentStats;
import com.example.backend.response.fields.EmployeeStats;
import com.example.backend.response.fields.JobStats;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stats {
    private EmployeeStats employeeStats;
    private JobStats jobStats;
    private DepartmentStats departmentStats;
    private CountryStats countryStats;
}
