package com.estuate.optim.OptimBackend.controller;

import com.estuate.optim.OptimBackend.service.DynamicDataSource;
import com.estuate.optim.OptimBackend.service.DynamicDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DynamicDataSourceService dynamicDataSourceService; // Use the correct service

    @PostMapping("/change")
    public String changeDatabase(@RequestParam String dbName) {
        dynamicDataSourceService.changeDatabase(dbName); // Ensure this method exists
        return "Database switched to: " + dbName;
    }
}
