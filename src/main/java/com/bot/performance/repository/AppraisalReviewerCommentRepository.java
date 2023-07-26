package com.bot.performance.repository;

import com.bot.performance.model.AppraisalReviewerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppraisalReviewerCommentRepository extends JpaRepository<AppraisalReviewerComment, Long> {
    @Query(nativeQuery = true, value = "select o.* from appraisal_reviewer_comments o order by o.AppraisalReviewerCommentsId desc limit 1")
    AppraisalReviewerComment getLastAppraisalReviewerComment();
}
