package com.bot.performance.service;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.model.AppraisalReviewDetail;
import com.bot.performance.model.AppraisalReviewFinalizerStatus;
import com.bot.performance.model.EmployeeSalaryDetail;
import com.bot.performance.repository.AppraisalDetailRepository;
import com.bot.performance.serviceinterface.IPromotionAndHikeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionAndHikeService implements IPromotionAndHikeService {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AppraisalDetailRepository appraisalDetailRepository;

    @Autowired
    DbManager dbManager;

    @Transactional
    public List<AppraisalReviewDetail> addPromotionAndHike(List<AppraisalReviewDetail> appraisalReviewDetails) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        long appraisalReviewId = dbManager.nextLongPrimaryKey(AppraisalReviewDetail.class) - 1;
        var activeAppraisalDetails = appraisalDetailRepository.getActiveAppraisalDetailRepository();
        if (activeAppraisalDetails == null)
            throw new Exception("Appraisal detail not found");

        List<AppraisalReviewFinalizerStatus> appraisalReviewFinalizer = new ArrayList<>();
        int AppraisalFinalizer = dbManager.nextIntPrimaryKey(AppraisalReviewFinalizerStatus.class) - 1;;
        for (var promotionDetail: appraisalReviewDetails) {
            if (promotionDetail.getEmployeeId() == 0)
                throw new Exception("Invalid employee selected");

            appraisalReviewId = appraisalReviewId + 1;
            var employeeSalaryDetail = dbManager.getById(promotionDetail.getEmployeeId(), EmployeeSalaryDetail.class);
            promotionDetail.setAppraisalDetailId(activeAppraisalDetails.getAppraisalDetailId());
            validateHikeDetail(promotionDetail, employeeSalaryDetail);
            promotionDetail.setAppraisalCycleStartDate(activeAppraisalDetails.getAppraisalCycleStartDate());
            promotionDetail.setAppraisalReviewId(appraisalReviewId);
            promotionDetail.setPreviousSalary(employeeSalaryDetail.getCTC());

            var result = appraisalDetailRepository.getApprovalChainRepository(promotionDetail.getEmployeeId());
            long finalAppraisalReviewId = appraisalReviewId;
            int i = 0;
            while (i < result.size()) {
                AppraisalFinalizer = AppraisalFinalizer + 1;
                var reviewerDetail = new AppraisalReviewFinalizerStatus();
                reviewerDetail.setAppraisalFinalizer(AppraisalFinalizer);
                reviewerDetail.setAppraisalReviewId(finalAppraisalReviewId);
                reviewerDetail.setReviwerId(result.get(i).getEmployeeUid());
                reviewerDetail.setEmail(result.get(i).getEmail());
                reviewerDetail.setFullName(result.get(i).getName());
                reviewerDetail.setActionRequired(result.get(i).isRequired());
                if (i+1 == 1) {
                    reviewerDetail.setStatus(9);
                    reviewerDetail.setReactedOn(utilDate);
                } else if (i+1 == 2) {
                    reviewerDetail.setStatus(2);
                } else  {
                    reviewerDetail.setStatus(0);
                }

                reviewerDetail.setApprovalLevel(i+1);
                appraisalReviewFinalizer.add( reviewerDetail);
                i++;
            }
        }
        dbManager.saveAll(appraisalReviewDetails, AppraisalReviewDetail.class);
        dbManager.saveAll(appraisalReviewFinalizer, AppraisalReviewFinalizerStatus.class);
        return appraisalReviewDetails;
    }

    private void validateHikeDetail(AppraisalReviewDetail appraisalReviewDetail, EmployeeSalaryDetail employeeSalaryDetail) throws Exception {
        BigDecimal proposedHikeAmount = (employeeSalaryDetail.getCTC().multiply(appraisalReviewDetail.getHikePercentage()))
                .divide(new BigDecimal(100));
        proposedHikeAmount = proposedHikeAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        if (!proposedHikeAmount.equals(appraisalReviewDetail.getHikeAmount()))
            throw new Exception("Proposed hike amount calculation mismatched");

        if (appraisalReviewDetail.getEstimatedSalary().subtract(employeeSalaryDetail.getCTC()).signum() <0)
            throw new Exception("Expected CTC is invalid");

        if (appraisalReviewDetail.getHikePercentage().signum() < 0)
            throw new Exception("Hike percentage is invalid");

        if (appraisalReviewDetail.getHikeAmount().signum() < 0)
            throw new Exception("Hike amount is invalid");

        if (appraisalReviewDetail.getEmployeeId() <= 0)
            throw new Exception("Employee detail not found");
    }
}
