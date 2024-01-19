package com.bot.performance.repository;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;

@Repository
public class AppraisalDetailRepository {
    @Autowired
    DbManager dbManager;
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    public AppraisalDetail getActiveAppraisalDetailRepository() {
        return dbManager.queryRaw("select o.* from appraisal_detail o where o.IsActiveCycle = 1", AppraisalDetail.class);
    }

    public List<AppraisalDetail> getAppraisalDetailRepository() {
        return dbManager.queryList("select o.* from appraisal_detail o", AppraisalDetail.class);
    }

    public List<EmployeeWithRoles> getApprovalChainRepository(long employeeId) throws Exception {
        String designationId = objectMapper.writeValueAsString(Arrays.asList((int) ApplicationConstant.Admin, (int) ApplicationConstant.Manager));
        var dataSet = lowLevelExecution.executeProcedure("sp_workflow_chain_by_emp_id",
                Arrays.asList(
                        new DbParameters("_EmployeeId", employeeId, Types.INTEGER),
                        new DbParameters("_DesignationIds", designationId, Types.VARCHAR)
                )
        );
        if (dataSet == null || dataSet.size() != 3)
            throw new Exception("Fail to get approval chain detail. Please contact to admin.");
//        List<ApprovalChainDetail> chainDetails = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<ApprovalChainDetail>>() {
//        });
//        if (chainDetails.isEmpty())
//            throw new Exception("Approval chain deatails not found. Please contact to admin");
        List<EmployeeWithRoles> employeeWithRoles = objectMapper.convertValue(dataSet.get("#result-set-2"), new TypeReference<List<EmployeeWithRoles>>() {
        });
        if (employeeWithRoles.size() == 0)
            throw new Exception("Reportee details not found. Please contact to admin");

        return employeeWithRoles;
    }

    public ObjectiveCatagory getObjectiveCategoryByEmpIdRepository(long employeeId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_EmployeeId", employeeId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_objective_catagory_by_emp_id", dbParameters);
        var result = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<ObjectiveCatagory>>() {});
        return  result.get(0);
    }

    public List<AppraisalReviewFinalizerStatus> getAppraisalFinalizerReviewRepository(long appraisalReviewId) {
        String query = String.format("select p.* from appraisal_review_finalizer_status p where p.AppraisalReviewId = %d", appraisalReviewId);
        return dbManager.queryList(query, AppraisalReviewFinalizerStatus.class);
    }

    public AppraisalReviewDetail getAppraisalReviewDetailRepository(long appraisalReviewId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_AppraisalReviewId", appraisalReviewId, Types.BIGINT));

        var dataSet = lowLevelExecution.executeProcedure("sp_appraisal_review_detail_by_id", dbParameters);
        List<AppraisalReviewDetail> data =  objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<AppraisalReviewDetail>>() {});
        if (data.size() > 0)
            return  data.get(0);
        else
            return  null;
    }

    public List<AppraisalReviewFinalizerStatus> getAppraisalReviewFinalizerRepository(long appraisalReviewId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_AppraisalReviewId", appraisalReviewId, Types.BIGINT));

        var dataSet = lowLevelExecution.executeProcedure("sp_appraisal_review_finalaizer_status_by_id", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<AppraisalReviewFinalizerStatus>>() {});
    }
}
