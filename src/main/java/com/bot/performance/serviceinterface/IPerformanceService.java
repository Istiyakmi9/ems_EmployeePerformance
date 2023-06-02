package com.bot.performance.serviceinterface;

import com.bot.performance.model.EmployeePerformance;
import com.bot.performance.model.EmployeeRole;
import com.bot.performance.model.FilterModel;
import com.bot.performance.model.PerfomanceObjective;
import org.javatuples.Pair;

import java.util.List;

public interface IPerformanceService {
    List<EmployeePerformance> GetAllEmpPerformanceService();
    List<PerfomanceObjective> ObjectiveInsertUpdateService(PerfomanceObjective objectiveDetail) throws Exception;
    EmployeePerformance UpdateEmployeeObjectiveService(EmployeePerformance employeePerformance) throws Exception;
    List<PerfomanceObjective> GetEmployeeObjectiveService(int designationId, int companyId, long employeeId) throws Exception;
    List<PerfomanceObjective> GetPerformanceObjectiveService(FilterModel filterModel) throws Exception;
    List<?> getEmployeeByManagerId(long managerId);
    String submitEmployeeObjectiveService(Long employeeId) throws Exception;
}
