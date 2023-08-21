package com.bot.performance.repository;

import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class PerformanceObjectiveRepository {
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    public List<ObjectiveDetail> getObjectiveByCatagoryIdRepository(int catagoryId, int companyId) {
        List<DbParameters> params = Arrays.asList(
                new DbParameters("_ObjectiveCatagoryId", catagoryId, Types.INTEGER),
                new DbParameters("_CompanyId", companyId, Types.INTEGER)
        );

        Map<String, Object> resultSet =  this.lowLevelExecution.executeProcedure("sp_objective_get_by_tag", params);
        return objectMapper.convertValue(resultSet.get("#result-set-1"), new TypeReference<List<ObjectiveDetail>>() {});
    }
}
