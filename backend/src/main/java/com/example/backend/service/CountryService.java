package com.example.backend.service;

import com.example.backend.request.AddNewCountryRequest;

import java.util.List;
import java.util.Map;

public interface CountryService {
    List<Map<String, Object>> getAllCountries();
    Map<String, Object> findCountryById(int id);
    int addCountry(AddNewCountryRequest request);
    int changeName(int id, String name);
    int changeRegion(int id, String region);
    int changeCurrencyCode(int id, String code);
}
