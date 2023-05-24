package com.bot.performance.controller;

import com.bot.performance.model.ApiResponse;
import com.bot.performance.model.FilterModel;
import com.bot.performance.model.ObjectiveCatagory;
import com.bot.performance.service.ApprisalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/eps/apprisalcatagory/")
public class ApprisalTypeController extends BaseController {
    @Autowired
    ApprisalTypeService apprisalTypeService;

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ResponseEntity<?> getAppraisalTypeFilter(@RequestBody FilterModel filter) {
        var result = this.apprisalTypeService.getAppraisalTypeByFilter(filter);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("addAppraisalType")
    public ResponseEntity<ApiResponse> addAppraisalType(@RequestBody ObjectiveCatagory objectiveCatagory) throws Exception {
        var result = this.apprisalTypeService.addAppraisalTypeService(objectiveCatagory);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("updateAppraisalType/{objectiveCatagoryId}")
    public ResponseEntity<ApiResponse> updateApprisalType(@RequestBody ObjectiveCatagory objectiveCatagory,
                                                          @PathVariable int objectiveCatagoryId) throws Exception {
        var result = this.apprisalTypeService.updateAppraisalTypeService(objectiveCatagory, objectiveCatagoryId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
