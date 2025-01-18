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
        String sql = "SELECT JOB_TITLE, MIN_SALARY, MAX_SALARY FROM JOBS WHERE JOB_ID = ?";
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
}
