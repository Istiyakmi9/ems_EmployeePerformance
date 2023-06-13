package com.bot.performance.service;

import com.bot.performance.model.*;
import com.bot.performance.repository.LowLevelExecution;
import com.bot.performance.repository.PerformanceObjectiveRepository;
import com.bot.performance.repository.PerformanceRepository;
import com.bot.performance.serviceinterface.IPerformanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService implements IPerformanceService {
    @Autowired
    PerformanceRepository performanceRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PerformanceObjectiveRepository performanceObjectiveRepository;
    @Autowired
    CurrentSession currentUserDetail;
    @Autowired
    LowLevelExecution lowLevelExecution;
    public List<EmployeePerformance> GetAllEmpPerformanceService() {
        return performanceRepository.findAll();
    }

    public List<?> getEmployeeByManagerId(long managerId) {
        return this.performanceRepository.getEmployeeByManagerId(managerId);
    }

    public List<PerfomanceObjective> GetEmployeeObjectiveService(int designationId, int companyId, long employeeId) throws Exception {
        if (designationId <= 0)
            throw new Exception("Invalid designation selected. Please login again");

        if (companyId <= 0)
            throw new Exception("Invalid company selected. Please login again");

        if (employeeId <= 0)
            throw new Exception("Invalid employee. Please login again");
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_EmployeeId", employeeId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_objective_get_by_employee", dbParameters);
        if (dataSet == null || dataSet.size() != 3)
            throw new Exception("Fail to get objectives. Please contact to admin.");
        List<PerfomanceObjective> empObjective =  objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<PerfomanceObjective>>() {});
        List<EmployeePerformance> empPerformance =  objectMapper.convertValue(dataSet.get("#result-set-2"), new TypeReference<List<EmployeePerformance>>() {});
        empObjective.forEach(x -> {
            if (empPerformance.size() > 0 && empObjective.size() > 0) {
                var objective = empPerformance.stream()
                        .filter(i -> i.getObjectiveId().equals(x.getObjectiveId()))
                        .findFirst();

                if (objective.isPresent()) {
                    var obj = objective.get();
                    x.setCurrentValue(obj.getCurrentValue());
                    x.setUpdatedOn(obj.getUpdatedOn());
                    x.setStatus(obj.getStatus());
                    x.setEmployeePerformanceId(obj.getEmployeePerformanceId());
                    x.setComments(obj.getComments());
                    x.setRating(obj.getRating());
                    x.setPerformanceStatus(obj.getPerformanceStatus());
                    try {
                        x.setPerformanceDetail(objectMapper.readValue(obj.getPerformanceDetail(), new TypeReference<ArrayList<PerformanceDetail>>() {
                        }));
                        x.setPerformanceDetail(x.getPerformanceDetail().stream()
                                .sorted((a, b) -> {
                                    return b.getUpdatedOn().compareTo(a.getUpdatedOn());
                                }).toList());
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
//        empObjective = performanceObjectiveRepository.getObjectivesByEmployeeId(employeeId);
//        if (empObjective.size() == 0)
//            return new ArrayList<>();

//        var empPerformanceObj = performanceRepository.getEmpPerformanceByEmpId(employeeId);
//        var companySettingDetail = companySettingRepository.findAll().stream().findFirst();
//        if(companySettingDetail.isEmpty()) {
//            throw new Exception("Company detail not found. Please contact to admin.");
//        }
//
//        var companyDetail  = companySettingDetail.get();
//        objectives.forEach(x -> {
//            if (!x.getTag().isEmpty() && x.getTag() != null) {
//                x.setDeclarationEndMonth(companyDetail.getDeclarationEndMonth());
//                x.setDeclarationStartMonth(companyDetail.getDeclarationStartMonth());
//                x.setFinancialYear(companyDetail.getFinancialYear());
//                var canSeeObject = true;
//                if (currentUserDetail.getUserDetail().getRoleId() == 2 && x.isObjSeeType())
//                   isObjSee = false;
//
//                try {
//                    x.setTagRole(objectMapper.readValue(x.getTag(), new TypeReference<List<Integer>>(){}));
//                } catch (JsonProcessingException e) {
//                    throw new RuntimeException(e);
//                }
//
//                var value = x.getTagRole().stream().filter(i -> i == designationId).toList();
//                if (value.size() > 0)
//                    empObjective.add(x);
//
//                if (empPerformanceObj.size() > 0 && empObjective.size() > 0) {
//                    var objective = empPerformanceObj.stream()
//                            .filter(i -> i.getObjectiveId().equals(x.getObjectiveId()))
//                            .findFirst();
//
//                    if (objective.isPresent()) {
//                        var obj = objective.get();
//                        x.setCurrentValue(obj.getCurrentValue());
//                        x.setUpdatedOn(obj.getUpdatedOn());
//                        x.setStatus(obj.getStatus());
//                        x.setEmployeePerformanceId(obj.getEmployeePerformanceId());
//                        x.setComments(obj.getComments());
//                        try {
//                            x.setPerformanceDetail(objectMapper.readValue(obj.getPerformanceDetail(), new TypeReference<ArrayList<PerformanceDetail>>(){}));
//                            x.setPerformanceDetail(x.getPerformanceDetail().stream()
//                                    .sorted((a, b) -> {
//                                       return b.getUpdatedOn().compareTo(a.getUpdatedOn());
//                                    }).toList());
//                        } catch (JsonProcessingException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            }
//        });

        return empObjective;
    }

    public EmployeePerformance UpdateEmployeeObjectiveService(EmployeePerformance employeePerformance) throws Exception {
        validateEmployeeObjective(employeePerformance);
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());

        EmployeePerformance existEmpPerformance = null;
        var performanceDetails = new ArrayList<PerformanceDetail>();
        PerformanceDetail performanceDetail = new PerformanceDetail();

        Optional<EmployeePerformance> existEmpPerformanceData = performanceRepository.findById(employeePerformance.getEmployeePerformanceId());
        if (existEmpPerformanceData.isPresent())
            existEmpPerformance =  existEmpPerformanceData.get();

        if (existEmpPerformance == null)
        {
            var lastPerformance = performanceRepository.getLastEmployeePerformance();
            existEmpPerformance = employeePerformance;

            if (lastPerformance == null) {
                existEmpPerformance.setEmployeePerformanceId(1L);
            }
            else {
                existEmpPerformance.setEmployeePerformanceId(lastPerformance.getEmployeePerformanceId()+1);
            }

            performanceDetail.setComments(employeePerformance.getComments());
            performanceDetail.setIndex(0);
            performanceDetail.setUpdatedOn(date);
        }
        else
        {
            if (existEmpPerformance.getPerformanceStatus() == ApplicationConstant.Submitted)
                throw new Exception("Objective already submitted");
            performanceDetails = (ArrayList<PerformanceDetail>) objectMapper.readValue(existEmpPerformance.getPerformanceDetail(), new TypeReference<List<PerformanceDetail>>(){});
            var index = performanceDetails.size();
            existEmpPerformance.setStatus(employeePerformance.getStatus());
            existEmpPerformance.setCurrentValue(employeePerformance.getCurrentValue());
            existEmpPerformance.setComments(employeePerformance.getComments());

            performanceDetail.setComments(employeePerformance.getComments());
            performanceDetail.setIndex(index);
        }
        performanceDetail.setStatus(employeePerformance.getStatus());
        performanceDetail.setCurrentValue(employeePerformance.getCurrentValue());
        performanceDetail.setUpdatedOn(date);
        performanceDetails.add(performanceDetail);
        existEmpPerformance.setPerformanceDetail(objectMapper.writeValueAsString(performanceDetails));
        existEmpPerformance.setUpdatedBy(currentUserDetail.getUserDetail().getUserId());
        existEmpPerformance.setPerformanceStatus(ApplicationConstant.Pending);
        existEmpPerformance.setRating(employeePerformance.getRating());
        existEmpPerformance.setUpdatedOn(date);

        return performanceRepository.save(existEmpPerformance);
    }

    public List<PerfomanceObjective> ObjectiveInsertUpdateService(PerfomanceObjective objectiveDetail) throws Exception {
        validateObjectiveDetail(objectiveDetail);
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        PerfomanceObjective objective;
        Optional<PerfomanceObjective> objectiveData = performanceObjectiveRepository.findById(objectiveDetail.getObjectiveId());
        if(objectiveData.isEmpty()) {
            var lastObjective = performanceObjectiveRepository.getLastPerformanceObjective();
            objective = objectiveDetail;
            if (lastObjective == null)
                objective.setObjectiveId(1L);
            else
                objective.setObjectiveId(lastObjective.getObjectiveId()+1);

            objective.setCreatedBy(currentUserDetail.getUserDetail().getUserId());
            objective.setCreatedOn(date);
        }
        else
        {
            objective = objectiveData.get();
            objective.setObjective(objectiveDetail.getObjective());
            objective.setStartValue(objectiveDetail.getStartValue());
            objective.setTargetValue(objectiveDetail.getTargetValue());
            objective.setCanManagerSee(objectiveDetail.isCanManagerSee());
            objective.setIncludeReview(objectiveDetail.isIncludeReview());
            objective.setProgressMeassureType(objectiveDetail.getProgressMeassureType());
            objective.setDescription(objectiveDetail.getDescription());
            objective.setTagRole(objectiveDetail.getTagRole());
            objective.setUpdatedOn(date);
            objective.setUpdatedBy(currentUserDetail.getUserDetail().getUserId());
        }

        performanceObjectiveRepository.save(objective);

        var filterModel = new FilterModel();
        filterModel.setSearchString(String.format("1=1 And CompanyId = %d", objective.getCompanyId()));
        filterModel.setPageSize(10);
        filterModel.setPageIndex(1);
        return this.GetPerformanceObjectiveService(filterModel);
    }

    public List<PerfomanceObjective> GetPerformanceObjectiveService(FilterModel filterModel) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_searchString", filterModel.getSearchString(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_sortBy", filterModel.getSortBy(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageIndex", filterModel.getPageIndex(), Types.INTEGER));
        dbParameters.add(new DbParameters("_pageSize", filterModel.getPageSize(), Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_performance_objective_getby_filter", dbParameters);
        if (dataSet == null || dataSet.size() != 2)
            throw new Exception("Fail to get objectives. Please contact to admin.");
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<PerfomanceObjective>>() {});
    }

    private void validateObjectiveDetail(@NotNull PerfomanceObjective objectiveDetail) throws Exception {
        if (objectiveDetail.getCompanyId() <= 0)
            throw new Exception("Invalid company selected. Please login again");

        if (objectiveDetail.getObjective() == null || objectiveDetail.getObjective().isEmpty())
            throw new Exception("Objective is null or empty");

        if (objectiveDetail.getProgressMeassureType() <= 0)
            throw new Exception("Invalid progress measured type selected");

        if (objectiveDetail.getProgressMeassureType() == 1)
        {
            if (objectiveDetail.getStartValue() < 0)
                throw new Exception("Invalid start value entered");

            if (objectiveDetail.getTargetValue() < 0)
                throw new Exception("Invalid target value entered");
        }
        else
        {
            objectiveDetail.setStartValue(0);
            objectiveDetail.setTargetValue(0);
        }
    }

    public String submitEmployeeObjectiveService(Long employeeId) throws Exception {
        if (employeeId  == 0)
            throw new Exception("Invalid employee performance selected");

        List<EmployeePerformance> performances = this.performanceRepository.getEmpPerformanceByEmpId(employeeId);
        if (performances.size() == 0)
            throw new Exception("No performance found. Please contact to admin");

        var submittedPerformance = performances.stream().filter(x -> x.getPerformanceStatus() == ApplicationConstant.Submitted).toList();
        if (submittedPerformance.size() > 0)
            throw new Exception("You already submitted your performance");

        performances.forEach(x -> {
            x.setPerformanceStatus(ApplicationConstant.Submitted);
        });
        this.performanceRepository.saveAll(performances);
        return "Performance submitted successfully";
    }

    private void validateEmployeeObjective(@NotNull EmployeePerformance employeePerformance) throws Exception {
        if (employeePerformance.getEmployeeId() <= 0)
            throw new Exception("Invalid employee. Please login again");

        if (employeePerformance.getCompanyId() <= 0)
            throw new Exception("Invalid company. Please login again");

        if (employeePerformance.getStatus() <= 0)
            throw new Exception("Invalid status. Please select a valid status");

        if (employeePerformance.getCurrentValue() < 0)
            throw new Exception("Invalid value entered. Please select a valid status");

        if (employeePerformance.getObjectiveId() <= 0)
            throw new Exception("Invalid objective selected. Please select a valid objective");

        if (employeePerformance.getCurrentValue() > employeePerformance.getTargetValue())
            throw new Exception("New value is greater than targeted value");
    }
}
