package com.bot.performance.service;

import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.DbParameters;
import com.bot.performance.model.ObjectiveDetail;
import com.bot.performance.model.PerfomanceObjective;
import com.bot.performance.repository.LowLevelExecution;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ObjectiveService {
    @Autowired
    private LowLevelExecution lowLevelExecution;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CurrentSession userDetail;

    public List<ObjectiveDetail> getObjectiveByCatagoryId(int catagoryId) {
        List<DbParameters> params = Arrays.asList(
            new DbParameters("_ObjectiveCatagoryId", catagoryId, Types.INTEGER),
            new DbParameters("_CompanyId", userDetail.getUserDetail().getCompanyId(), Types.INTEGER)
        );

         Map<String, Object> resultSet =  this.lowLevelExecution.executeProcedure("sp_objective_get_by_tag", params);
        return objectMapper.convertValue(resultSet.get("#result-set-1"), new TypeReference<List<ObjectiveDetail>>() {});
    }
}
