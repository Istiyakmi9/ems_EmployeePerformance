package com.bot.performance.controller;

import com.bot.performance.model.*;
import com.bot.performance.serviceinterface.IApprisalTyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/eps/apprisalcatagory/")
public class ApprisalTypeController extends BaseController {
    @Autowired
    IApprisalTyeService apprisalTypeService;

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ResponseEntity<?> getAppraisalTypeFilter(@RequestBody FilterModel filter) throws Exception {
        var result = this.apprisalTypeService.getAppraisalTypeByFilter(filter);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("addAppraisalType")
    public ResponseEntity<ApiResponse> addAppraisalType(@RequestBody AppraisalAndCategoryDTO appraisalAndCategoryDTO) throws Exception {
        var result = this.apprisalTypeService.addAppraisalTypeService(appraisalAndCategoryDTO);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("updateAppraisalType/{objectiveCatagoryId}")
    public ResponseEntity<ApiResponse> updateApprisalType(@RequestBody AppraisalAndCategoryDTO appraisalAndCategoryDTO,
                                                          @PathVariable int objectiveCatagoryId) throws Exception {
        var result = this.apprisalTypeService.updateAppraisalTypeService(appraisalAndCategoryDTO, objectiveCatagoryId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("manageAppraisalCycle/{objectiveCatagoryId}")
    public ResponseEntity<ApiResponse> manageAppraisalCycle(@RequestBody ObjectiveCatagory objectiveCatagory,
                                                          @PathVariable int objectiveCatagoryId) throws Exception {
        var result = this.apprisalTypeService.manageAppraisalCycleService(objectiveCatagory, objectiveCatagoryId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getObjectiveByCategoryId/{objectiveCategotyId}")
    public ResponseEntity<ApiResponse> getObjectiveByCategoryId(@PathVariable int objectiveCategotyId) throws Exception {
        var result = this.apprisalTypeService.getObjectiveByCategoryIdService(objectiveCategotyId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getCategoryByCategoryId/{objectiveCategotyId}")
    public ResponseEntity<ApiResponse> getCategoryByCategoryId(@PathVariable int objectiveCategotyId) throws Exception {
        var result = this.apprisalTypeService.getAppraisalDetailAndCategoryService(objectiveCategotyId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("manageAppraisalCategory")
    public ResponseEntity<ApiResponse> manageAppraisalCategory(@RequestBody AppraisalDetail appraisalDetail) throws Exception {
        var result = this.apprisalTypeService.manageAppraisalCategoryStatus(appraisalDetail);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("manageAppraisalLevel")
    public ResponseEntity<ApiResponse> manageAppraisalLevel(@RequestBody List<AppraisalChainLevel> appraisalLevels) throws Exception {
        var result = this.apprisalTypeService.manageAppraisalLevel(appraisalLevels);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
