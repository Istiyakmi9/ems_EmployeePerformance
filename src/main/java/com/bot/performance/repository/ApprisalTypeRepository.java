package com.bot.performance.repository;

import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ApprisalTypeRepository {
    @Autowired
    LowLevelExecution lowLevelExecution;

    @Autowired
    ObjectMapper objectMapper;

    public List<ObjectiveCatagory> getAppraisalTypeByFilterRepository(FilterModel filter) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();

        dbParameters.add(new DbParameters("_sortBy", filter.getSortBy(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageIndex", filter.getPageIndex(), Types.INTEGER));
        dbParameters.add(new DbParameters("_pageSize", filter.getPageSize(), Types.INTEGER));
        dbParameters.add(new DbParameters("_objectiveCatagoryType", filter.getObjectiveCatagoryType(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_typeDescription", filter.getTypeDescription(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_rolesid", filter.getRolesId(), Types.VARCHAR));

        var dataSet = lowLevelExecution.executeProcedure("sp_objective_catagory_filter", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<ObjectiveCatagory>>() { });
    }

    public List<ObjectiveDetail> getObjectiveByCategoryIdRepository(int objectiveCategotyId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_ObjectiveCatagoryId", objectiveCategotyId, Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_objective_get_by_role", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<ObjectiveDetail>>() {});
    }

    public Map<String, Object> getAppraisalDetailAndCategoryRepository(int objectiveCategoryId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_ObjectiveCatagoryId", objectiveCategoryId, Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_objective_catagory_appraisal_detail_byid", dbParameters);
        List<AppraisalAndCategoryDTO> result =  objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference< List<AppraisalAndCategoryDTO>>() {});
        if (result.size() > 0) {
            result.forEach(x -> {
                try {
                    x.setRoleIds(objectMapper.readValue(x.getRolesId(), new TypeReference<List<Integer>>() {}));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    List<ObjectiveCatagory> objectiveCatagories = objectMapper.convertValue(dataSet.get("#result-set-2"), new TypeReference< List<ObjectiveCatagory>>() {});
    List<ApprovalWorkFlow> approvalWorkFlows = objectMapper.convertValue(dataSet.get("#result-set-3"), new TypeReference< List<ApprovalWorkFlow>>() {});
    if (approvalWorkFlows.size() == 0)
        throw new Exception("Approval work flow not found. Please contact to admin");

    Map<String, Object> response = new HashMap<>();
    response.put("AppraisalCategory", result);
    response.put("ObjectiveCategory", objectiveCatagories);
    response.put("ApprovalWorkflow", approvalWorkFlows);
    return  response;
    }
}