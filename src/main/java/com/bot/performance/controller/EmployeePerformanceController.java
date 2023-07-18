package com.bot.performance.controller;

import com.bot.performance.model.*;
import com.bot.performance.serviceinterface.IPerformanceService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/eps/performance/")
public class EmployeePerformanceController extends BaseController {

    @Autowired
    CurrentSession userDetail;

    @Autowired
    IPerformanceService performanceService;
    // @Value("${file.folder:na}")
    @Value("${file.folder:#{null}}")
    private String targetFolder;

    Logger logger = LoggerFactory.getLogger(EmployeePerformanceController.class);

    @GetMapping("get")
    @CircuitBreaker(name = "countryList", fallbackMethod = "handleOnNoService")
    public ResponseEntity<List<String>> getDetail() {
        System.out.println(userDetail.getUserDetail().toString());
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

    @GetMapping("getemployeebymanagerid/{managerId}")
    public ResponseEntity<List<?>> getallEmpPerformance(@PathVariable("managerId") long managerId) {
        var result = performanceService.getEmployeeByManagerId(managerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("getall")
    public ResponseEntity<List<EmployeePerformance>> getallEmpPerformance() {
        var result = performanceService.GetAllEmpPerformanceService();
        return ResponseEntity.ok(result);
    }

    @PostMapping("updateEmployeeObjective")
    public  ResponseEntity<ApiResponse> updateEmployeeObjective(@RequestBody EmployeePerformance employeePerformance) throws Exception {
        var result = performanceService.UpdateEmployeeObjectiveService(employeePerformance);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getEmployeeObjective/{designationId}/{companyId}/{employeeId}")
    public  ResponseEntity<ApiResponse> getEmployeeObjective(@PathVariable("designationId") int designationId,
                                                                           @PathVariable("companyId") int companyId,
                                                                           @PathVariable("employeeId") long employeeId) throws Exception {
        var result = performanceService.GetEmployeeObjectiveService(designationId, companyId, employeeId);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("getPerformanceObjective")
    public  ResponseEntity<ApiResponse> getPerformanceObjective(@RequestBody FilterModel filterModel) throws Exception {
        var result = performanceService.GetPerformanceObjectiveService(filterModel);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("objectiveInsertUpdate")
    public  ResponseEntity<ApiResponse> objectiveInsertUpdate(@RequestBody PerfomanceObjective perfomanceObjective) throws Exception {
        var result = performanceService.ObjectiveInsertUpdateService(perfomanceObjective);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    public ResponseEntity<List<String>> handleOnNoService(Exception ex) {
        logger.error("Fallback method called as service is not available");
        var items = new ArrayList<String>();
        items.add("No country");
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @GetMapping("submitEmployeeObjective/{employeeId}")
    public  ResponseEntity<ApiResponse> submitEmployeeObjective(@PathVariable Long employeeId) throws Exception {
        var result = performanceService.submitEmployeeObjectiveService(employeeId);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("changeEmployeeObjectiveStatus/{employeeId}/{status}")
    public  ResponseEntity<ApiResponse> changeEmployeeObjectiveStatus(@PathVariable Long employeeId,
                                                                @PathVariable int status) throws Exception {
        var result = performanceService.changeEmployeeObjectiveStatusService(employeeId, status);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
