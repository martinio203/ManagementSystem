package com.example.backend.response.fields;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CountryStats {
    private Map<String, Object> mostCountries;
    private Map<String, Object> mostLocations;
    private int countedCountries;
}
