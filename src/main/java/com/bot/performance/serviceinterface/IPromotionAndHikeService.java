package com.bot.performance.serviceinterface;

import com.bot.performance.model.AppraisalReviewDetail;

import java.util.List;

public interface IPromotionAndHikeService {
    AppraisalReviewDetail addPromotionAndHike(List<AppraisalReviewDetail> appraisalReviewDetail) throws Exception;
}
