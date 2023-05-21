package com.bot.performance.service;

import com.bot.performance.model.*;
import com.bot.performance.repository.CompanySettingRepository;
import com.bot.performance.repository.EmployeeRoleRepository;
import com.bot.performance.repository.PerformanceObjectiveRepository;
import com.bot.performance.repository.PerformanceRepository;
import com.bot.performance.serviceinterface.IPerformanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    EmployeeRoleRepository employeeRoleRepository;
    @Autowired
    CompanySettingRepository companySettingRepository;
    public List<EmployeePerformance> GetAllEmpPerformanceService() {
        var result = performanceRepository.findAll();
        return result;
    }

    public List<?> getEmployeeByManagerId(long managerId) {
        var apprisalEmployeeDetails = this.performanceRepository.getEmployeeByManagerId(managerId);
        return apprisalEmployeeDetails;
    }

    public List<PerfomanceObjective> GetEmployeeObjectiveService(int designationId, int companyId, long employeeId) throws Exception {
        var empObjective = new ArrayList<PerfomanceObjective>();
        if (designationId <= 0)
            throw new Exception("Invalid designation selected. Please login again");

        if (companyId <= 0)
            throw new Exception("Invalid company selected. Please login again");

        if (employeeId <= 0)
            throw new Exception("Invalid employee. Please login again");

        var objectives = performanceObjectiveRepository.findAll();
        if (objectives.size() == 0)
            throw new Exception("Performance objective not found");

        var empPerformanceObj = performanceRepository.getEmpPerformanceByEmpId(employeeId);
        var companySettingDetail = companySettingRepository.findAll().stream().findFirst();
        if(companySettingDetail.isEmpty()) {
            throw new Exception("Company detail not found. Please contact to admin.");
        }

        var companyDetail  = companySettingDetail.get();
        objectives.forEach(x -> {
            if (!x.getTag().isEmpty() && x.getTag() != null) {
                x.setDeclarationEndMonth(companyDetail.getDeclarationEndMonth());
                x.setDeclarationStartMonth(companyDetail.getDeclarationStartMonth());
                x.setFinancialYear(companyDetail.getFinancialYear());
                var canSeeObject = true;
//                if (currentUserDetail.getUserDetail().getRoleId() == 2 && x.isObjSeeType())
//                    isObjSee = false;

                try {
                    x.setTagRole(objectMapper.readValue(x.getTag(), new TypeReference<List<Integer>>(){}));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                var value = x.getTagRole().stream().filter(i -> i == designationId).toList();
                if (value.size() > 0)
                    empObjective.add(x);

                if (empPerformanceObj.size() > 0 && empObjective.size() > 0) {
                    var objective = empPerformanceObj.stream()
                            .filter(i -> i.getObjectiveId().equals(x.getObjectiveId()))
                            .findFirst();

                    if (objective.isPresent()) {
                        var obj = objective.get();
                        x.setCurrentValue(obj.getCurrentValue());
                        x.setUpdatedOn(obj.getUpdatedOn());
                        x.setStatus(obj.getStatus());
                        x.setEmployeePerformanceId(obj.getEmployeePerformanceId());
                        x.setComments(obj.getComments());
                        try {
                            x.setPerformanceDetail(objectMapper.readValue(obj.getPerformanceDetail(), new TypeReference<ArrayList<PerformanceDetail>>(){}));
                            x.setPerformanceDetail(x.getPerformanceDetail().stream()
                                    .sorted((a, b) -> {
                                       return b.getUpdatedOn().compareTo(a.getUpdatedOn());
                                    }).toList());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
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
        }
        else
        {
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
        performanceDetails.add(performanceDetail);
        existEmpPerformance.setPerformanceDetail(objectMapper.writeValueAsString(performanceDetails));
        existEmpPerformance.setUpdatedBy(currentUserDetail.getUserDetail().getUserId());
        existEmpPerformance.setUpdatedOn(date);

        var result = performanceRepository.save(existEmpPerformance);
        if (result == null)
            throw new Exception("Fail to update employee objective");

        return result;
    }

    public Pair<List<PerfomanceObjective>, List<EmployeeRole>> ObjectiveInsertUpdateService(PerfomanceObjective objectiveDetail) throws Exception {
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
            objective.setObjSeeType(objectiveDetail.isObjSeeType());
            objective.setIncludeReview(objectiveDetail.isIncludeReview());
            objective.setProgressMeassureType(objectiveDetail.getProgressMeassureType());
            objective.setTimeFrameStart(objectiveDetail.getTimeFrameStart());
            objective.setTimeFrmaeEnd(objectiveDetail.getTimeFrmaeEnd());
            objective.setObjectiveType(objectiveDetail.getObjectiveType());
            objective.setDescription(objectiveDetail.getDescription());
            objective.setTagRole(objectiveDetail.getTagRole());
            objective.setUpdatedOn(date);
            objective.setUpdatedBy(currentUserDetail.getUserDetail().getUserId());
        }

        if (objective.getTagRole() != null && objective.getTagRole().size() > 0)
            objective.setTag(objectMapper.writeValueAsString(objective.getTagRole()));
        else
            objective.setTag("[]");

        var result = performanceObjectiveRepository.save(objective);
        if (result == null)
            throw new Exception("Fail to insert/update objective deatils");

        var filterModel = new FilterModel();
        filterModel.setCompanyId(objective.getCompanyId());
        return this.GetPerformanceObjectiveService(filterModel);
    }

    public Pair<List<PerfomanceObjective>, List<EmployeeRole>> GetPerformanceObjectiveService(@NotNull FilterModel filterModel) throws Exception {
        Pageable paging;
        filterModel.setPageIndex(filterModel.getPageIndex()-1);
        if (filterModel.getSortBy() == null || filterModel.getSortBy() == "")
            paging = PageRequest.of(filterModel.getPageIndex(), filterModel.getPageSize());
        else {
            Sort sort = filterModel.getSortDirection().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(filterModel.getSortBy()).ascending()
                    : Sort.by(filterModel.getSortBy()).descending();
            paging = PageRequest.of(filterModel.getPageIndex(), filterModel.getPageSize(), sort);
        }

        Page<PerfomanceObjective> page;
        if (filterModel.getSearchString() == null || filterModel.getSearchString() == "") {
            page = performanceObjectiveRepository.findAll(paging);
        } else {
            page = performanceObjectiveRepository.findByFilter(filterModel.getSearchString(), paging);
        }
        List<PerfomanceObjective> objectiveDetails = page.getContent();
        objectiveDetails.forEach(x -> {
            if (x.getTag() != null && x.getTag() != "[]") {
                try {
                    x.setTagRole(objectMapper.readValue(x.getTag(), new TypeReference<List<Integer>>() {
                    }));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            x.setTotal(page.getTotalElements());
        });
        var empRole = employeeRoleRepository.findAll();
        if (empRole == null || empRole.size() <= 0)
            throw new Exception("Employee role not found. Please contact to admin");

        return new Pair<List<PerfomanceObjective>, List<EmployeeRole>>(objectiveDetails, empRole);
    }

    private void validateObjectiveDetail(@NotNull PerfomanceObjective objectiveDetail) throws Exception {
        if (objectiveDetail.getCompanyId() <= 0)
            throw new Exception("Invalid company selected. Please login again");

        if (objectiveDetail.getObjective() == null || objectiveDetail.getObjective().isEmpty())
            throw new Exception("Objective is null or empty");

        if (objectiveDetail.getTimeFrameStart() == null)
            throw new Exception("Invalid time frame start date selected");

        if (objectiveDetail.getTimeFrmaeEnd() == null)
            throw new Exception("Invalid time frame end date selected");

        if (objectiveDetail.getObjectiveType() == null || objectiveDetail.getObjectiveType().isEmpty())
            throw new Exception("Objective type is invalid");

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
