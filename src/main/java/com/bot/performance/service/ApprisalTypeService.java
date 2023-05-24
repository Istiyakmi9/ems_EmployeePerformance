package com.bot.performance.service;

import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.FilterModel;
import com.bot.performance.model.ObjectiveCatagory;
import com.bot.performance.repository.ApprisalTypeRepository;
import com.bot.performance.serviceinterface.IApprisalTyeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprisalTypeService implements IApprisalTyeoService {

    @Autowired
    ApprisalTypeRepository apprisalTypeRepository;
    @Autowired
    CurrentSession currentUserDetail;

    @Override
    public List<ObjectiveCatagory> getAppraisalTypeByFilter(FilterModel filter) {
        return this.apprisalTypeRepository.getApprisalTypeQuery(filter.getSearchString(),
                filter.getSortBy(), filter.getPageIndex(), filter.getPageSize());
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
        var result = apprisalTypeRepository.save(objectiveCatagory);
        if (result == null)
            throw new Exception("Fail to insert appraisal category");

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
        existObjectiveCatagory.setFromDate(objectiveCatagory.getFromDate());
        existObjectiveCatagory.setToDate(objectiveCatagory.getToDate());
        existObjectiveCatagory.setUpdatedBy(currentUserDetail.getUserDetail().getUserId());
        existObjectiveCatagory.setUpdatedOn(date);
        var result = apprisalTypeRepository.save(objectiveCatagory);
        if (result == null)
            throw new Exception("Fail to update appraisal category");

        FilterModel filterModel = new FilterModel();
        return  this.getAppraisalTypeByFilter(filterModel);
    }

    private void validateApprisalType(ObjectiveCatagory objectiveCatagory) throws Exception {
        if (objectiveCatagory.getObjectiveCatagoryType().isEmpty())
            throw new Exception("Please enter objective category first");

        if (objectiveCatagory.getTypeDescription().isEmpty())
            throw new Exception("Please enter description first");

        if (objectiveCatagory.getFromDate() == null)
            throw new Exception("Please select a valid from date");

        if (objectiveCatagory.getToDate() == null)
            throw new Exception("Please select a valid to date");
    }
}
