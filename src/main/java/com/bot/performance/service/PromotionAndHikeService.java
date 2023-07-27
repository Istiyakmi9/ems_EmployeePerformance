package com.bot.performance.service;

import com.bot.performance.model.AppraisalReviewDetail;
import com.bot.performance.model.AppraisalReviewerComment;
import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.EmployeeSalaryDetail;
import com.bot.performance.repository.AppraisalDetailRepository;
import com.bot.performance.repository.AppraisalReviewRepository;
import com.bot.performance.repository.AppraisalReviewerCommentRepository;
import com.bot.performance.repository.EmployeeSalaryDetailRepository;
import com.bot.performance.serviceinterface.IPromotionAndHikeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PromotionAndHikeService implements IPromotionAndHikeService {
    @Autowired
    EmployeeSalaryDetailRepository employeeSalaryDetailRepository;
    @Autowired
    AppraisalReviewRepository appraisalReviewRepository;
    @Autowired
    AppraisalReviewerCommentRepository appraisalReviewerCommentRepository;
    @Autowired
    CurrentSession currentUserDetail;
    @Autowired
    AppraisalDetailRepository appraisalDetailRepository;
    @Transactional(rollbackOn = Exception.class)
    public AppraisalReviewDetail addPromotionAndHike(List<AppraisalReviewDetail> appraisalReviewDetails) throws Exception {
        long appraisalReviewId = 0;
        long appraisalReviewerCommentsId = 0;
        List<AppraisalReviewerComment> appraisalReviewerComments = new ArrayList<AppraisalReviewerComment>();
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        var appraisalReviewDetail = appraisalReviewRepository.getLastAppraisalReviewDetail();
        if (appraisalReviewDetail != null)
            appraisalReviewId = appraisalReviewDetail.getAppraisalDetailId() + 1;

        var appraisalReviewerDetail = appraisalReviewerCommentRepository.getLastAppraisalReviewerComment();
        if (appraisalReviewerDetail != null)
            appraisalReviewerCommentsId = appraisalReviewerDetail.getAppraisalReviewerCommentsId();

        var activeAppraisalDetails = appraisalDetailRepository.getActiveAppraisalDetail();
        if (activeAppraisalDetails == null)
            throw new Exception("Appraisal detail not found");

        for (var promotionDetail: appraisalReviewDetails) {
            if (promotionDetail.getEmployeeId() == 0)
                throw new Exception("Invalid employee selected");

            promotionDetail.setAppraisalDetailId(activeAppraisalDetails.getAppraisalDetailId());
            promotionDetail.setAppraisalCycleStartDate(activeAppraisalDetails.getAppraisalCycleStartDate());

            appraisalReviewId = appraisalReviewId + 1;
            var employeeData =employeeSalaryDetailRepository.findById(promotionDetail.getEmployeeId());
            if (employeeData.isEmpty())
                throw new Exception("Employee detail not found");

            var employeeSalaryDetail = employeeData.get();
            validateHikeDetail(promotionDetail, employeeSalaryDetail);
            promotionDetail.setAppraisalReviewId(appraisalReviewId);
            promotionDetail.setPreviousSalary(employeeSalaryDetail.getCTC());
            promotionDetail.setAppraisalReviewId(appraisalReviewId);
            if (!promotionDetail.getComments().isEmpty() || promotionDetail.getRating().signum() > 0) {
                appraisalReviewerCommentsId = appraisalReviewerCommentsId + 1;
                appraisalReviewerComments.add(addAppraisalReviewerComment(promotionDetail, date, appraisalReviewerCommentsId));
            }
        }
        appraisalReviewRepository.saveAll(appraisalReviewDetails);
        if (appraisalReviewerComments.size() > 0)
            appraisalReviewerCommentRepository.saveAll(appraisalReviewerComments);

        return  null;
    }

    private AppraisalReviewerComment addAppraisalReviewerComment(AppraisalReviewDetail appraisalReviewDetail, Date date,
                                                                 long appraisalReviewerCommentsId) {
        AppraisalReviewerComment appraisalReviewerComment = new AppraisalReviewerComment();
        appraisalReviewerComment.setComments(appraisalReviewDetail.getComments());
        appraisalReviewerComment.setRating(appraisalReviewDetail.getRating());
        appraisalReviewerComment.setReactedOn(date);
        appraisalReviewerComment.setAppraisalReviewId(appraisalReviewDetail.getAppraisalReviewId());
        appraisalReviewerComment.setReviewerId(currentUserDetail.getUserDetail().getUserId());
        appraisalReviewerComment.setAppraisalReviewerCommentsId(appraisalReviewerCommentsId);
        return appraisalReviewerComment;
    }

    private void validateHikeDetail(AppraisalReviewDetail appraisalReviewDetail, EmployeeSalaryDetail employeeSalaryDetail) throws Exception {
        if (appraisalReviewDetail.getAppraisalDetailId() == 0)
            throw new Exception("Invalid appraisal selected");

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
    }
}
