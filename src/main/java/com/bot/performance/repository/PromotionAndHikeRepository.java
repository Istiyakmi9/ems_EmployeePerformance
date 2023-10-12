package com.bot.performance.repository;

import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.ApplicationConstant;
import com.bot.performance.model.AppraisalReviewDetailDTO;
import com.bot.performance.model.DbParameters;
import com.bot.performance.model.TeamMemberAndAppraisalFinalizer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PromotionAndHikeRepository {
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    public List<AppraisalReviewDetailDTO> getPromotionAndHikeRepository(long employeeId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_EmployeeId", employeeId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_appraisal_detail_finalizer_id", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<AppraisalReviewDetailDTO>>() {
        });
    }

    public  List<TeamMemberAndAppraisalFinalizer> getApprovePromotionAndHikeRepository() throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_Status", (int)ApplicationConstant.Approved, Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_project_appraisal_detai_finalize", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<TeamMemberAndAppraisalFinalizer>>() {
        });
    }
}
