package com.example.backend.controller;

import com.example.backend.request.AddNewCountryRequest;
import com.example.backend.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
        return ResponseEntity.ok(countryService.findCountryById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countCountries() {
        return ResponseEntity.ok(countryService.countCountries());
    }

    @GetMapping("/most-locations")
    public ResponseEntity<Map<String, Object>> mostLocationsInCountry() {
        return ResponseEntity.ok(countryService.mostLocationsInCountry());
    }

    @GetMapping("/most-countries")
    public ResponseEntity<Map<String, Object>> mostCountriesInRegion() {
        return ResponseEntity.ok(countryService.mostCountriesInRegion());
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addNew(@RequestBody AddNewCountryRequest request) {
        return ResponseEntity.ok(countryService.addCountry(request));
    }

    @PatchMapping("/change-name/{id}")
    public ResponseEntity<Integer> changeName(@PathVariable int id,
                                              @RequestBody String name) {
        return ResponseEntity.ok(countryService.changeName(id, name));
    }

    @PatchMapping("/change-region/{id}")
    public ResponseEntity<Integer> changeRegionName(@PathVariable int id,
                                                    @RequestBody String regionName) {
        return ResponseEntity.ok(countryService.changeRegion(id, regionName));
    }

    @PatchMapping("/change-currency-code/{id}")
    public ResponseEntity<Integer> changeCurrencyCode(@PathVariable int id,
                                              @RequestBody String code) {
        return ResponseEntity.ok(countryService.changeCurrencyCode(id, code));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteCountry(@PathVariable int id) {
        return ResponseEntity.ok(countryService.deleteCountry(id));
    }
}
