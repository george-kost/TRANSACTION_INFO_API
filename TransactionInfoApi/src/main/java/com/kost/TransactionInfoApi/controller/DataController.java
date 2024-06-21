package com.kost.TransactionInfoApi.controller;

import com.kost.TransactionInfoApi.service.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataLoader dataLoader;

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshData() {
        try {
            dataLoader.loadData();
            return ResponseEntity.ok("Data refreshed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error refreshing data: " + e.getMessage());
        }
    }
}