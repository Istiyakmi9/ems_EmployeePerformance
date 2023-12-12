package com.bot.performance.repository;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.*;
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
    @Autowired
    DbManager dbManager;

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

    public List<ApprovalChainDetail> getAppraisalChainLevelRepository(int objectiveCatagoryId, long employeeId, int companyId, int projectId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_EmployeeId", employeeId, Types.BIGINT));
        dbParameters.add(new DbParameters("_FindParents", true, Types.BIT));
        dbParameters.add(new DbParameters("_CompanyId", companyId, Types.INTEGER));
        dbParameters.add(new DbParameters("_ProjectId", projectId, Types.INTEGER));
        dbParameters.add(new DbParameters("_ObjectiveCatagoryId", objectiveCatagoryId, Types.INTEGER));

        var dataSet = lowLevelExecution.executeProcedure("sp_org_hierarchy_chail_discover", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<ApprovalChainDetail>>() {});
    }
}
