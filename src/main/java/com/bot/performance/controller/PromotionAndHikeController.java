package com.bot.performance.controller;

import com.bot.performance.model.ApiResponse;
import com.bot.performance.model.AppraisalReviewDetail;
import com.bot.performance.serviceinterface.IPromotionAndHikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eps/promotion/")
public class PromotionAndHikeController {
    @Autowired
    IPromotionAndHikeService iPromotionAndHikeService;

    @PostMapping("addPromotionAndHike")
    public ResponseEntity<ApiResponse> addPromotionAndHike(@RequestBody List<AppraisalReviewDetail> appraisalReviewDetails) throws Exception {
        var result = iPromotionAndHikeService.addPromotionAndHike(appraisalReviewDetails);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getPromotionAndHike/{employeeId}")
    public ResponseEntity<ApiResponse> getPromotionAndHike(@PathVariable long employeeId) throws Exception {
        var result = iPromotionAndHikeService.getPromotionAndHikeService(employeeId);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getApprovePromotionAndHike")
    public ResponseEntity<ApiResponse> getApprovePromotionAndHike() throws Exception {
        var result = iPromotionAndHikeService.getApprovePromotionAndHikeService();
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @RequestMapping(value = "reOpenAppraisalObjective/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse> reOpenAppraisalObjective(@PathVariable("userId") Long userId, @RequestBody List<Integer> reviewIds) throws Exception {
        var result = iPromotionAndHikeService.reOpenAppraisalObjectiveService(userId, reviewIds);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
