package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AboutController {

    @GetMapping("/sobre")
    public ResponseEntity<Map<String, String>> getAboutInfo() {
        Map<String, String> about = new HashMap<>();
        about.put("estudante", "Willian Minatto");
        about.put("projeto", "projetoBackend");
        return new ResponseEntity<>(about, HttpStatus.OK);
    }
}
