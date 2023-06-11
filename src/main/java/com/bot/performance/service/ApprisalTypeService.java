package com.bot.performance.service;

import com.bot.performance.model.*;
import com.bot.performance.repository.ApprisalTypeRepository;
import com.bot.performance.repository.LowLevelExecution;
import com.bot.performance.serviceinterface.IApprisalTyeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApprisalTypeService implements IApprisalTyeService {

    @Autowired
    ApprisalTypeRepository apprisalTypeRepository;
    @Autowired
    CurrentSession currentUserDetail;
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<ObjectiveCatagory> getAppraisalTypeByFilter(FilterModel filter) {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_searchString", filter.getSearchString(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_sortBy", filter.getSortBy(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageIndex", filter.getPageIndex(), Types.INTEGER));
        dbParameters.add(new DbParameters("_pageSize", filter.getPageSize(), Types.INTEGER));

        var dataSet = lowLevelExecution.executeProcedure("sp_objective_catagory_filter", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<ObjectiveCatagory>>() {});
    }

    @Override
    public List<ObjectiveCatagory> addAppraisalTypeService(ObjectiveCatagory objectiveCatagory) throws Exception {
        validateApprisalType(objectiveCatagory);
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        var lastObjectiveCatagory = apprisalTypeRepository.getLastObjectiveCategory();
        if (lastObjectiveCatagory == null)
            objectiveCatagory.setObjectiveCatagoryId(1);
        else
            objectiveCatagory.setObjectiveCatagoryId(lastObjectiveCatagory.getObjectiveCatagoryId() + 1);
        objectiveCatagory.setCreatedBy(currentUserDetail.getUserDetail().getUserId());
        objectiveCatagory.setCreatedOn(date);
        objectiveCatagory.setObjectivesId("[]");
        objectiveCatagory.setRolesId("[]");
        apprisalTypeRepository.save(objectiveCatagory);

        FilterModel filterModel = new FilterModel();
        return  this.getAppraisalTypeByFilter(filterModel);
    }

    @Override
    public List<ObjectiveCatagory> updateAppraisalTypeService(ObjectiveCatagory objectiveCatagory, int objectiveCatagoryId) throws Exception {
        if (objectiveCatagoryId == 0)
            throw new Exception("Invalid appraisal selected");

        validateApprisalType(objectiveCatagory);
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        var existObjectiveCatagoryData = apprisalTypeRepository.findById(objectiveCatagoryId);
        if (existObjectiveCatagoryData.isEmpty())
            throw new Exception("Objective category not found");

        ObjectiveCatagory existObjectiveCatagory = existObjectiveCatagoryData.get();
        existObjectiveCatagory.setObjectiveCatagoryType(objectiveCatagory.getObjectiveCatagoryType());
        existObjectiveCatagory.setTypeDescription(objectiveCatagory.getTypeDescription());
        existObjectiveCatagory.setAppraisalCycleFromDate(objectiveCatagory.getAppraisalCycleFromDate());
        existObjectiveCatagory.setAppraisalCycleToDate(objectiveCatagory.getAppraisalCycleToDate());
        existObjectiveCatagory.setSelfAppraisalFromDate(objectiveCatagory.getSelfAppraisalFromDate());
        existObjectiveCatagory.setSelfAppraisalToDate(objectiveCatagory.getSelfAppraisalToDate());
        existObjectiveCatagory.setTagByRole(objectiveCatagory.isTagByRole());
        existObjectiveCatagory.setTagByDepartment(objectiveCatagory.isTagByDepartment());
        existObjectiveCatagory.setSelfAppraisal(objectiveCatagory.isSelfAppraisal());
        existObjectiveCatagory.setMultiRaterFeedback(objectiveCatagory.isMultiRaterFeedback());
        existObjectiveCatagory.setSelectionPeriodFromDate(objectiveCatagory.getSelectionPeriodFromDate());
        existObjectiveCatagory.setSelectionPeriodFromDate(objectiveCatagory.getSelectionPeriodFromDate());
        existObjectiveCatagory.setFeedbackFromDate(objectiveCatagory.getFeedbackFromDate());
        existObjectiveCatagory.setFeedbackToDate(objectiveCatagory.getFeedbackToDate());
        existObjectiveCatagory.setDefaultRater(objectiveCatagory.isDefaultRater());
        existObjectiveCatagory.setAllowSelfAppraisal(objectiveCatagory.isAllowSelfAppraisal());
        existObjectiveCatagory.setHikeApproval(objectiveCatagory.isHikeApproval());
        existObjectiveCatagory.setReviewFromDate(objectiveCatagory.getReviewFromDate());
        existObjectiveCatagory.setReviewToDate(objectiveCatagory.getReviewToDate());
        existObjectiveCatagory.setNormalizationFromDate(objectiveCatagory.getNormalizationFromDate());
        existObjectiveCatagory.setNormalizationToDate(objectiveCatagory.getNormalizationToDate());
        existObjectiveCatagory.setUpdatedBy(currentUserDetail.getUserDetail().getUserId());
        existObjectiveCatagory.setUpdatedOn(date);
        apprisalTypeRepository.save(existObjectiveCatagory);

        FilterModel filterModel = new FilterModel();
        filterModel.setSearchString("1=1");
        filterModel.setPageSize(10);
        filterModel.setPageIndex(1);
        return  this.getAppraisalTypeByFilter(filterModel);
    }

    private void validateApprisalType(ObjectiveCatagory objectiveCatagory) throws Exception {
        if (objectiveCatagory.getObjectiveCatagoryType().isEmpty())
            throw new Exception("Please enter objective category first");

        if (objectiveCatagory.getTypeDescription().isEmpty())
            throw new Exception("Please enter description first");

        if (objectiveCatagory.getSelfAppraisalToDate() == null)
            throw new Exception("Please select a valid from date");

        if (objectiveCatagory.getAppraisalCycleToDate() == null)
            throw new Exception("Please select a valid to date");
    }

    @Override
    public String manageAppraisalCycleService(ObjectiveCatagory objectiveCatagory, int objectiveCatagoryId) throws Exception {
        if (objectiveCatagoryId == 0)
            throw new Exception("Invalid appraisal selected");

        validateApprisalType(objectiveCatagory);
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        var existObjectiveCatagoryData = apprisalTypeRepository.findById(objectiveCatagoryId);
        if (existObjectiveCatagoryData.isEmpty())
            throw new Exception("Objective category not found");

        ObjectiveCatagory existObjectiveCatagory = existObjectiveCatagoryData.get();
        if (objectiveCatagory.getObjectiveIds() != null)
            existObjectiveCatagory.setObjectivesId(objectMapper.writeValueAsString(objectiveCatagory.getObjectiveIds()));

        existObjectiveCatagory.setUpdatedOn(date);
        apprisalTypeRepository.save(existObjectiveCatagory);
        return "successful";
    }

    public List<ObjectiveDetail> getObjectiveByCategoryIdService(int objectiveCategotyId) throws Exception {
        if (objectiveCategotyId == 0)
            throw new Exception("Invalid objective selected.");

        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_ObjectiveCatagoryId", objectiveCategotyId, Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_objective_get_by_role", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<ObjectiveDetail>>() {});
    }


}
