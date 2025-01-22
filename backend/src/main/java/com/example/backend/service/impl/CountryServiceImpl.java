package com.example.backend.service.impl;

import com.example.backend.repository.CountryRepository;
import com.example.backend.request.AddNewCountryRequest;
import com.example.backend.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public List<Map<String, Object>> getAllCountries() {
        return countryRepository.findAllCountries();
    }

    @Override
    public Map<String, Object> findCountryById(int id) {
        return countryRepository.findCountryById(id);
    }

    @Override
    public int addCountry(AddNewCountryRequest request) {
        return countryRepository.createCountry(request);
    }

    @Override
    public int changeName(int id, String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("Country name can not be empty");
        return countryRepository.changeName(id, name);
    }

    @Override
    public int changeRegion(int id, String region) {
        if (region.isEmpty()) throw new IllegalArgumentException("Region name can not be empty");
        return countryRepository.changeRegion(id, region);
    }

    @Override
    public int changeCurrencyCode(int id, String code) {
        if (code.isEmpty()) throw new IllegalArgumentException("Currency code can not be empty");
        return countryRepository.changeCurrencyCode(id, code);
    }

    @Override
    public int countCountries() {
        return countryRepository.countCountries();
    }

    @Override
    public Map<String, Object> mostLocationsInCountry() {
        return countryRepository.mostLocations();
    }

    @Override
    public Map<String, Object> mostCountriesInRegion() {
        return countryRepository.mostCountries();
    }

    @Override
    public int deleteCountry(int id) {
        return countryRepository.deleteCountry(id);
    }
}
