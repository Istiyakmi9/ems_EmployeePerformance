package com.bot.performance.controller;

import com.bot.performance.model.GitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/eps/test/")
public class TestController {

    @Autowired
    GitConfiguration gitConfiguration;

    @GetMapping("get")
    public ResponseEntity<List<String>> get() {
        System.out.println("Application called................");
        List<String> names = new ArrayList<>();
        names.add("Admin");
        names.add("User");
        names.add("Tester");
        names.add("Manager");
        names.add("Developer");
        names.add(gitConfiguration.getName());
        names.add(gitConfiguration.getFolder());

        return ResponseEntity.ok(names);
    }
}
