package com.bot.performance.controller;

import com.bot.performance.model.EmployeePerformance;
import com.bot.performance.model.FilterModel;
import com.bot.performance.model.PerfomanceObjective;
import com.bot.performance.service.PerformanceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/performance/")
public class EmployeePerformanceController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PerformanceService performanceService;
    // @Value("${file.folder:na}")
    @Value("${file.folder:#{null}}")
    private String targetFolder;

    Logger logger = LoggerFactory.getLogger(EmployeePerformanceController.class);

    @GetMapping("get")
    @CircuitBreaker(name = "countryList", fallbackMethod = "handleOnNoService")
    public ResponseEntity<List<String>> getDetail() {
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

    @GetMapping("getall")
    public ResponseEntity<List<EmployeePerformance>> getallEmpPerformance() {
        var result = performanceService.GetAllEmpPerformanceService();
        return ResponseEntity.ok(result);
    }

    @PostMapping("updateEmployeeObjective")
    public  ResponseEntity<EmployeePerformance> updateEmployeeObjective(@RequestBody EmployeePerformance employeePerformance) throws Exception {
        var result = performanceService.UpdateEmployeeObjectiveService(employeePerformance);
        return  ResponseEntity.ok(result);
    }

    @GetMapping("getEmployeeObjective/{designationId}/{companyId}/{employeeId}")
    public  ResponseEntity<List<PerfomanceObjective>> getEmployeeObjective(@PathVariable("designationId") int designationId,
                                                                           @PathVariable("companyId") int companyId,
                                                                           @PathVariable("employeeId") long employeeId) throws Exception {
        var result = performanceService.GetEmployeeObjectiveService(designationId, companyId, employeeId);
        return  ResponseEntity.ok(result);
    }

    @PostMapping("getPerformanceObjective")
    public  ResponseEntity<List<PerfomanceObjective>> getPerformanceObjective(@RequestBody FilterModel filterModel) throws Exception {
        var result = performanceService.GetPerformanceObjectiveService(filterModel);
        return  ResponseEntity.ok(result);
    }

    @PostMapping("objectiveInsertUpdate")
    public  ResponseEntity<List<PerfomanceObjective>> objectiveInsertUpdate(@RequestBody PerfomanceObjective perfomanceObjective) throws Exception {
        var result = performanceService.ObjectiveInsertUpdateService(perfomanceObjective);
        return  ResponseEntity.ok(result);
    }

    public ResponseEntity<List<String>> handleOnNoService(Exception ex) {
        logger.error("Fallback method called as service is not available");
        var items = new ArrayList<String>();
        items.add("No country");
        return new ResponseEntity<>(items, HttpStatus.OK);

    }
}
