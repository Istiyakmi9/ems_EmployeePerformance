package com.bot.performance.controller;

import com.bot.performance.model.CurrentUserDetail;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/performance/")
public class EmployeePerformanceController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CurrentUserDetail userDetail;

    // @Value("${file.folder:na}")
    @Value("${file.folder:#{null}}")
    private String targetFolder;

    Logger logger = LoggerFactory.getLogger(EmployeePerformanceController.class);

    @GetMapping("get")
    @CircuitBreaker(name = "countryList", fallbackMethod = "handleOnNoService")
    public ResponseEntity<List<String>> getDetail() {
        logger.info(userDetail.getEmail());
        List<String> names = new ArrayList<>();
        names.add("India");
        names.add("America");
        names.add("Turkey");
        names.add("Japan");
        names.add("Russia");
        names.add(targetFolder);

//        String msg = restTemplate.getForObject("http://PERFORMANCE-DATA-SERVICE/data/sample", String.class);
//        if(msg != null) {
//            names.add(msg);
//        }

        return ResponseEntity.ok(names);
    }

    public ResponseEntity<List<String>> handleOnNoService(Exception ex) {
        logger.error("Fallback method called as service is not available");
        var items = new ArrayList<String>();
        items.add("No country");
        return new ResponseEntity<>(items, HttpStatus.OK);

    }
}
