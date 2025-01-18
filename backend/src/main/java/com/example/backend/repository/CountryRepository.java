package com.example.backend.repository;


import com.example.backend.request.AddNewCountryRequest;

import java.util.List;
import java.util.Map;

public interface CountryRepository {
    List<Map<String, Object>> findAllCountries();
    Map<String, Object> findCountryById(int id);
    int createCountry(AddNewCountryRequest request);
    int changeName(int id, String name);
    int changeRegion(int id, String region);
    int changeCurrencyCode(int id, String code);

}

