package com.bot.performance.serviceinterface;

import com.bot.performance.model.FilterModel;
import com.bot.performance.model.ObjectiveCatagory;
import com.bot.performance.model.ObjectiveDetail;
import com.bot.performance.repository.AppraisalAndCategoryDTO;

import java.util.List;

public interface IApprisalTyeService {
    List<ObjectiveCatagory> getAppraisalTypeByFilter(FilterModel filter);
    List<ObjectiveCatagory> addAppraisalTypeService(AppraisalAndCategoryDTO appraisalAndCategoryDTO) throws Exception;
    List<ObjectiveCatagory> updateAppraisalTypeService(AppraisalAndCategoryDTO appraisalAndCategoryDTO, int objectiveCatagoryId) throws Exception;
    String manageAppraisalCycleService(ObjectiveCatagory objectiveCatagory, int objectiveCatagoryId) throws Exception;
    List<ObjectiveDetail> getObjectiveByCategoryIdService(int objectiveCategoryId) throws Exception;
    List<AppraisalAndCategoryDTO> getAppraisalDetailAndCategoryService(int objectiveCategoryId) throws Exception;
}
