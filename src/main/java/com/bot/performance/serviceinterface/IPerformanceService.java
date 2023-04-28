package com.bot.performance.serviceinterface;

import com.bot.performance.model.EmployeePerformance;
import com.bot.performance.model.FilterModel;
import com.bot.performance.model.PerfomanceObjective;

import java.util.List;

public interface IPerformanceService {
    List<EmployeePerformance> GetAllEmpPerformanceService();
    List<PerfomanceObjective> ObjectiveInsertUpdateService(PerfomanceObjective objectiveDetail) throws Exception;
    EmployeePerformance UpdateEmployeeObjectiveService(EmployeePerformance employeePerformance) throws Exception;
    List<PerfomanceObjective> GetEmployeeObjectiveService(int designationId, int companyId, long employeeId) throws Exception;
    List<PerfomanceObjective> GetPerformanceObjectiveService(FilterModel filterModel);
}
