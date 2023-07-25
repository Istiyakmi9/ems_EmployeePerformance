package com.bot.performance.repository;

import com.bot.performance.model.AppraisalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppraisalDetailRepository extends JpaRepository<AppraisalDetail, Integer> {
    @Query(nativeQuery = true, value = "select o.* from appraisal_detail o order by o.AppraisalDetailId desc limit 1")
    AppraisalDetail getLastAppraisalDetail();
}
