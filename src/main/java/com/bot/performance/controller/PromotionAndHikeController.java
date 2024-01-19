package com.bot.performance.controller;

import com.bot.performance.model.ApiResponse;
import com.bot.performance.model.AppraisalReviewDetail;
import com.bot.performance.model.AppraisalReviewDetailDTO;
import com.bot.performance.model.AppraisalReviewFinalizerStatus;
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

    @RequestMapping(value = "reOpenEmployeeObjective/{employeeId}/{appraisalReviewDetail}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse> reOpenEmployeeObjective(@PathVariable("employeeId") Long employeeId,
                                                               @PathVariable("appraisalReviewDetail") int appraisalReviewDetail) throws Exception {
        var result = iPromotionAndHikeService.reOpenEmployeeObjectiveService(employeeId, appraisalReviewDetail);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @RequestMapping(value = "approveAppraisalReviewDetail", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> approveAppraisalReviewDetail(@RequestBody List<AppraisalReviewDetailDTO> appraisalReviewDetails) throws Exception {
        var result = iPromotionAndHikeService.approveAppraisalReviewDetailService(appraisalReviewDetails);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @RequestMapping(value = "rejectAppraisalReviewDetail", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> rejectAppraisalReviewDetail(@RequestBody List<AppraisalReviewDetailDTO> appraisalReviewDetails) throws Exception {
        var result = iPromotionAndHikeService.rejectAppraisalReviewDetailService(appraisalReviewDetails);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @RequestMapping(value = "revisedAppraisal", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> revisedAppraisal(@RequestBody List<AppraisalReviewFinalizerStatus> appraisalReviewFinalizerStatus) throws Exception {
        var result = iPromotionAndHikeService.revisedAppraisalService(appraisalReviewFinalizerStatus);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
