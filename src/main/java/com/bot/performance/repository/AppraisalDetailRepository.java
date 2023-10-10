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
}
