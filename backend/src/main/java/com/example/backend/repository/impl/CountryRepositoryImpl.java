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
        String sql = "SELECT C.COUNTRY_ID, C.COUNTRY_NAME, R.REGION_NAME " +
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

    @Override
    public int countCountries() {
        String sql = "SELECT COUNT(*) FROM COUNTRIES";
        Integer count = jdbc.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public Map<String, Object> mostLocations() {
        String sql = "SELECT COUNTRY_NAME, LOCATIONS_COUNT " +
                "FROM (SELECT C.COUNTRY_NAME, COUNT(*) AS LOCATIONS_COUNT " +
                "FROM COUNTRIES C " +
                "JOIN LOCATIONS L ON C.COUNTRY_ID = L.COUNTRY_ID " +
                "GROUP BY C.COUNTRY_NAME " +
                "ORDER BY LOCATIONS_COUNT DESC) " +
                "WHERE ROWNUM = 1";
        return jdbc.queryForMap(sql);
    }

    @Override
    public Map<String, Object> mostCountries(){
        String sql = "SELECT REGION_NAME, COUNTRIES_COUNT " +
                "FROM(SELECT R.REGION_NAME, COUNT(*) AS COUNTRIES_COUNT " +
                "FROM REGIONS R " +
                "JOIN COUNTRIES C ON R.REGION_ID = C.REGION_ID " +
                "GROUP BY R.REGION_NAME " +
                "ORDER BY COUNTRIES_COUNT DESC) " +
                "WHERE ROWNUM = 1";
        return jdbc.queryForMap(sql);
    }

    @Override
    public int deleteCountry(int id) {
        String sql = "DELETE FROM COUNTRIES WHERE COUNTRY_ID = ?";
        return jdbc.update(sql, id);
    }
}
