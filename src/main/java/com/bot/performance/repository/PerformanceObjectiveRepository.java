package com.bot.performance.repository;

import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.DbParameters;
import com.bot.performance.model.ObjectiveDetail;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class PerformanceObjectiveRepository {
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    public List<ObjectiveDetail> getObjectiveByCatagoryIdRepository(int catagoryId, int companyId) throws Exception {
        List<DbParameters> params = Arrays.asList(
                new DbParameters("_ObjectiveCatagoryId", catagoryId, Types.INTEGER),
                new DbParameters("_CompanyId", companyId, Types.INTEGER)
        );

        Map<String, Object> resultSet =  this.lowLevelExecution.executeProcedure("sp_objective_get_by_tag", params);
        return objectMapper.convertValue(resultSet.get("#result-set-1"), new TypeReference<List<ObjectiveDetail>>() {});
    }
}
