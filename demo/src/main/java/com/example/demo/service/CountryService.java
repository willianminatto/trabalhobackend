package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountryService {

    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getCountryData(String countryName) {
        try {
            String url = "https://restcountries.com/v3.1/name/" + countryName;
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode countryInfo = root.get(0);

            String commonName = countryInfo.get("name").get("common").asText();
            String officialName = countryInfo.get("name").get("official").asText();
            String capital = countryInfo.get("capital").get(0).asText();
            long population = countryInfo.get("population").asLong();

            Map<String, Object> countryData = new HashMap<>();
            countryData.put("commonName", commonName);
            countryData.put("officialName", officialName);
            countryData.put("capital", capital);
            countryData.put("population", population);

            return countryData;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar os dados do país.", e);
        }
    }
}
