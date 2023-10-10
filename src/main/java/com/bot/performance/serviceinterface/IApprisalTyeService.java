package com.bot.performance.serviceinterface;

import com.bot.performance.model.FilterModel;
import com.bot.performance.model.ObjectiveCatagory;
import com.bot.performance.model.ObjectiveDetail;
import com.bot.performance.model.AppraisalAndCategoryDTO;

import java.util.List;
import java.util.Map;

public interface IApprisalTyeService {
    List<ObjectiveCatagory> getAppraisalTypeByFilter(FilterModel filter) throws Exception;
    List<ObjectiveCatagory> addAppraisalTypeService(AppraisalAndCategoryDTO appraisalAndCategoryDTO) throws Exception;
    List<ObjectiveCatagory> updateAppraisalTypeService(AppraisalAndCategoryDTO appraisalAndCategoryDTO, int objectiveCatagoryId) throws Exception;
    String manageAppraisalCycleService(ObjectiveCatagory objectiveCatagory, int objectiveCatagoryId) throws Exception;
    List<ObjectiveDetail> getObjectiveByCategoryIdService(int objectiveCategoryId) throws Exception;
    Map<String, Object> getAppraisalDetailAndCategoryService(int objectiveCategoryId) throws Exception;
}
