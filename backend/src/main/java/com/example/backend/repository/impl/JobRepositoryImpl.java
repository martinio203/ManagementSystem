package com.example.backend.repository.impl;

import com.example.backend.repository.JobRepository;
import com.example.backend.request.AddNewJobRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JobRepositoryImpl implements JobRepository {

    private final JdbcTemplate jdbc;

    @Override
    public List<Map<String, Object>> findAllJobs() {
        String sql = "SELECT * FROM JOBS";
        return jdbc.queryForList(sql);
    }

    @Override
    public Map<String, Object> findJobById(String id) {
        String sql = "SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY FROM JOBS WHERE JOB_ID = ?";
        return jdbc.queryForMap(sql, id);
    }

    @Override
    public int createNewJob(AddNewJobRequest request) {
        String sql = "INSERT INTO JOBS(JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY) " +
                "VALUES (?, ?, ?, ?)";
        return jdbc.update(sql,
                request.getJobId(),
                request.getJobTitle(),
                request.getMinSalary(),
                request.getMaxSalary()
        );
    }

    @Override
    public int changeTitle(String id, String title) {
        String sql = "UPDATE JOBS SET JOB_TITLE = ? WHERE JOB_ID = ?";
        return jdbc.update(sql, title, id);
    }

    @Override
    public int changeMinSalary(String id, int salary) {
        String sql = "UPDATE JOBS SET MIN_SALARY = ? WHERE JOB_ID = ?";
        return jdbc.update(sql, salary, id);
    }

    @Override
    public int changeMaxSalary(String id, int salary) {
        String sql = "UPDATE JOBS SET MAX_SALARY = ? WHERE JOB_ID = ?";
        return jdbc.update(sql, salary, id);
    }

    @Override
    public int countJobs() {
        String sql = "SELECT COUNT(*) FROM JOBS";
        Integer count = jdbc.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public Map<String, Object> maxSalaryJob() {
        String sql = "SELECT JOB_TITLE, MAX_SALARY " +
                "FROM jobs " +
                "WHERE max_salary = (SELECT MAX(max_salary) FROM jobs)";
        return jdbc.queryForMap(sql);
    }

    @Override
    public Map<String, Object> minSalaryJob() {
        String sql = "SELECT job_title, min_salary " +
                "FROM jobs " +
                "WHERE min_salary = (SELECT MIN(min_salary) FROM jobs)";
        return jdbc.queryForMap(sql);
    }

    @Override
    public Map<String, Object> mostEmployeesJob() {
        String sql = "SELECT JOB_TITLE, EMPLOYEES_COUNT " +
                "FROM ( SELECT J.JOB_TITLE, COUNT(E.EMPLOYEE_ID) AS EMPLOYEES_COUNT " +
                "FROM JOBS J " +
                "JOIN EMPLOYEES E ON J.JOB_ID = E.JOB_ID " +
                "GROUP BY J.JOB_TITLE " +
                "ORDER BY EMPLOYEES_COUNT DESC) " +
                "WHERE ROWNUM = 1";
        return jdbc.queryForMap(sql);
    }

    @Override
    public int deleteJob(String id) {
        String sql = "DELETE FROM JOBS WHERE JOB_ID = ?";
        return jdbc.update(sql, id);
    }
}
