package com.bot.performance.repository;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.DbParameters;
import com.bot.performance.model.EmployeePerformance;
import com.bot.performance.model.FilterModel;
import com.bot.performance.model.PerfomanceObjective;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PerformanceRepository {
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DbManager dbManager;

    public List<?> getEmployeeByManagerIdRepository(long objectiveCategotyId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_ReportingManagerId", objectiveCategotyId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_employee_performance_by_managerid_get", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<Map<String, Object>>>() {
        });
    }

    public Map<String, Object> getEmployeeNObjectivesRepository(long employeeId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_EmployeeId", employeeId, Types.BIGINT));
        Map<String, Object> dataSet = lowLevelExecution.executeProcedure("sp_objective_get_by_employee", dbParameters);
        if (dataSet == null || dataSet.size() != 3)
            throw new Exception("Fail to get objectives. Please contact to admin.");

        return dataSet;
    }

    public List<PerfomanceObjective> getPerformanceObjectiveRepository(FilterModel filterModel) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_searchString", filterModel.getSearchString(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_sortBy", filterModel.getSortBy(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageIndex", filterModel.getPageIndex(), Types.INTEGER));
        dbParameters.add(new DbParameters("_pageSize", filterModel.getPageSize(), Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_performance_objective_getby_filter", dbParameters);
        if (dataSet == null || dataSet.size() != 2)
            throw new Exception("Fail to get objectives. Please contact to admin.");
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<PerfomanceObjective>>() {
        });
    }

    public List<EmployeePerformance> getEmployeePerformanceByIdRepository(long employeeId) {
        String query = "select p.* from employee_performance p where p.employeeId  =" + employeeId;
        var result = dbManager.queryList(query, EmployeePerformance.class);
        return objectMapper.convertValue(result, new TypeReference<List<EmployeePerformance>>() {
        });
    }
}
