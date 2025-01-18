package com.example.backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddNewCountryRequest {
    private int countryId;
    private String countryName;
    private int regionId;
    private String currencyCode;
}
