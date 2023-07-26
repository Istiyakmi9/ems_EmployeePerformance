package com.bot.performance.repository;

import com.bot.performance.model.AppraisalReviewDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppraisalReviewRepository extends JpaRepository<AppraisalReviewDetail, Long> {
    @Query(nativeQuery = true, value = "select o.* from appraisal_review_detail o order by o.AppraisalReviewId desc limit 1")
    AppraisalReviewDetail getLastAppraisalReviewDetail();
}
