package com.example.demo.controller;

import com.example.demo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country/{name}")
    public ResponseEntity<Map<String, Object>> getCountryInfo(@PathVariable String name) {
        try {
            Map<String, Object> countryInfo = countryService.getCountryData(name);
            return new ResponseEntity<>(countryInfo, HttpStatus.OK);
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>(Map.of("error", "País não encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Erro ao buscar país"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/country")
    public ResponseEntity<Map<String, Object>> postCountryInfo(@RequestBody Map<String, String> countryData) {
        String countryName = countryData.get("name");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Dados recebidos");
        response.put("country", countryName);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
