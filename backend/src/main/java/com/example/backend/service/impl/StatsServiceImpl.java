package com.example.backend.service.impl;

import com.example.backend.repository.CountryRepository;
import com.example.backend.repository.DepartmentRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.repository.JobRepository;
import com.example.backend.response.Stats;
import com.example.backend.response.fields.CountryStats;
import com.example.backend.response.fields.DepartmentStats;
import com.example.backend.response.fields.EmployeeStats;
import com.example.backend.response.fields.JobStats;
import com.example.backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;
    private final CountryRepository countryRepository;

    private EmployeeStats employeeStats() {
        return new EmployeeStats(
                employeeRepository.countEmployees(),
                employeeRepository.avgSalary()
        );
    }

    private JobStats jobStats() {
        return new JobStats(
                jobRepository.countJobs(),
                jobRepository.minSalaryJob(),
                jobRepository.maxSalaryJob(),
                jobRepository.mostEmployeesJob()
        );
    }

    private DepartmentStats departmentStats() {
        return new DepartmentStats(
                departmentRepository.mostEmployees(),
                departmentRepository.countDepartments()
        );
    }

    private CountryStats countryStats() {
        return new CountryStats(
                countryRepository.mostCountries(),
                countryRepository.mostLocations(),
                countryRepository.countCountries()
        );
    }

    @Override
    public Stats stats() {
        return new Stats(
                employeeStats(),
                jobStats(),
                departmentStats(),
                countryStats()
        );
    }

}
