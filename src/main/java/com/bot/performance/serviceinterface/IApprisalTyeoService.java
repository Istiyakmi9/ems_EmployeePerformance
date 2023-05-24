package com.bot.performance.serviceinterface;

import com.bot.performance.model.FilterModel;
import com.bot.performance.model.ObjectiveCatagory;

import java.util.List;

public interface IApprisalTyeoService {
    List<ObjectiveCatagory> getAppraisalTypeByFilter(FilterModel filter);
    List<ObjectiveCatagory> addAppraisalTypeService(ObjectiveCatagory objectiveCatagory) throws Exception;
    List<ObjectiveCatagory> updateAppraisalTypeService(ObjectiveCatagory objectiveCatagory, int objectiveCatagoryId) throws Exception;
}
