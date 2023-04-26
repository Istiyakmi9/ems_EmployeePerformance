package com.bot.performance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/performance/api/")
public class PerformanceController {
    @RequestMapping("getall")
    public ResponseEntity<List<String>> getAll() {
        System.out.println("Application called................");
        List<String> names = new ArrayList<>();
        names.add("Admin");
        names.add("User");
        names.add("Tester");
        names.add("Manager");
        names.add("Developer");

        return ResponseEntity.ok(names);
    }
}
