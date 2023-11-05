package com.bot.performance.serviceinterface;

import com.bot.performance.model.AppraisalReviewDetail;
import com.bot.performance.model.AppraisalReviewDetailDTO;
import com.bot.performance.model.TeamMemberAndAppraisalFinalizer;

import java.util.List;

public interface IPromotionAndHikeService {
    List<AppraisalReviewDetail> addPromotionAndHike(List<AppraisalReviewDetail> appraisalReviewDetail) throws Exception;
    List<AppraisalReviewDetailDTO> getPromotionAndHikeService(long employeeId) throws Exception;
    List<TeamMemberAndAppraisalFinalizer> getApprovePromotionAndHikeService() throws Exception;
    String reOpenAppraisalObjectiveService(Long userId, List<Integer> reviewIds) throws Exception;
}
