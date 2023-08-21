package com.bot.performance.service;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.model.AppraisalReviewDetail;
import com.bot.performance.model.AppraisalReviewerComment;
import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.EmployeeSalaryDetail;
import com.bot.performance.repository.AppraisalDetailRepository;
import com.bot.performance.serviceinterface.IPromotionAndHikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PromotionAndHikeService implements IPromotionAndHikeService {
    @Autowired
    CurrentSession currentUserDetail;
    @Autowired
    AppraisalDetailRepository appraisalDetailRepository;

    @Autowired
    DbManager dbManager;

    @Transactional
    public AppraisalReviewDetail addPromotionAndHike(List<AppraisalReviewDetail> appraisalReviewDetails) throws Exception {
        long appraisalReviewId = 0;
        List<AppraisalReviewerComment> appraisalReviewerComments = new ArrayList<AppraisalReviewerComment>();
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        appraisalReviewId = dbManager.nextLongPrimaryKey(AppraisalReviewDetail.class);

        long appraisalReviewerCommentsId = dbManager.nextLongPrimaryKey(AppraisalReviewDetail.class);
        var activeAppraisalDetails = appraisalDetailRepository.getActiveAppraisalDetailRepository();
        if (activeAppraisalDetails == null)
            throw new Exception("Appraisal detail not found");

        for (var promotionDetail: appraisalReviewDetails) {
            if (promotionDetail.getEmployeeId() == 0)
                throw new Exception("Invalid employee selected");

            promotionDetail.setAppraisalDetailId(activeAppraisalDetails.getAppraisalDetailId());
            promotionDetail.setAppraisalCycleStartDate(activeAppraisalDetails.getAppraisalCycleStartDate());

            appraisalReviewId = appraisalReviewId + 1;
            var employeeSalaryDetail = dbManager.getById(promotionDetail.getEmployeeId(), EmployeeSalaryDetail.class);
            validateHikeDetail(promotionDetail, employeeSalaryDetail);
            promotionDetail.setAppraisalReviewId(appraisalReviewId);
            promotionDetail.setPreviousSalary(employeeSalaryDetail.getCTC());
            promotionDetail.setAppraisalReviewId(appraisalReviewId);
            if (!promotionDetail.getComments().isEmpty() || promotionDetail.getRating().signum() > 0) {
                appraisalReviewerCommentsId = appraisalReviewerCommentsId + 1;
                appraisalReviewerComments.add(addAppraisalReviewerComment(promotionDetail, date, appraisalReviewerCommentsId));
            }
        }

        dbManager.saveAll(appraisalReviewDetails, AppraisalReviewDetail.class);
        if (appraisalReviewerComments.size() > 0)
            dbManager.saveAll(appraisalReviewerComments, AppraisalReviewerComment.class);

        return null;
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
