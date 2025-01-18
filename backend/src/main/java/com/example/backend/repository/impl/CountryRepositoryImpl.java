package com.example.backend.repository.impl;

import com.example.backend.repository.CountryRepository;
import com.example.backend.request.AddNewCountryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final JdbcTemplate jdbc;

    @Override
    public List<Map<String, Object>> findAllCountries() {
        String sql = "SELECT C.COUNTRY_NAME, R.REGION_NAME, C.CURRENCY_CODE " +
                "FROM COUNTRIES C " +
                "JOIN REGIONS R ON C.REGION_ID = R.REGION_ID";
        return jdbc.queryForList(sql);
    }

    @Override
    public Map<String, Object> findCountryById(int id) {
        String sql = "SELECT C.COUNTRY_NAME, R.REGION_NAME, C.CURRENCY_CODE " +
                "FROM COUNTRIES C " +
                "JOIN REGIONS R ON C.REGION_ID = R.REGION_ID " +
                "WHERE COUNTRY_ID = ?";
        return jdbc.queryForMap(sql, id);
    }

    @Override
    public int createCountry(AddNewCountryRequest request) {
        String sql = "INSERT INTO COUNTRIES(COUNTRY_ID, COUNTRY_NAME, REGION_ID, CURRENCY_CODE) " +
                "VALUES(?, ?, ?, ?)";
        return jdbc.update(sql,
                request.getCountryId(),
                request.getCountryName(),
                request.getRegionId(),
                request.getCurrencyCode()
        );
    }

    @Override
    public int changeName(int id, String name) {
        String sql = "UPDATE COUNTRIES SET COUNTRY_NAME = ? " +
                "WHERE COUNTRY_ID = ?";
        return jdbc.update(sql, name, id);
    }

    @Override
    public int changeRegion(int id, String region) {
        String sql = "UPDATE COUNTRIES SET REGION_ID = " +
                "(SELECT REGION_ID FROM REGIONS " +
                "WHERE REGION_NAME = ?) " +
                "WHERE COUNTRY_ID = ?";

        return jdbc.update(sql, region, id);
    }

    @Override
    public int changeCurrencyCode(int id, String code) {
        String sql = "UPDATE COUNTRIES SET CURRENCY_CODE = ?" +
                " WHERE COUNTRY_ID = ?";
        return jdbc.update(sql, code, id);
    }
}
