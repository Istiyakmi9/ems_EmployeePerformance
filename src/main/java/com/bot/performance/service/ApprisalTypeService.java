package com.bot.performance.service;

import com.bot.performance.config.ApplicationException;
import com.bot.performance.model.*;
import com.bot.performance.model.AppraisalAndCategoryDTO;
import com.bot.performance.repository.AppraisalDetailRepository;
import com.bot.performance.repository.ApprisalTypeRepository;
import com.bot.performance.repository.LowLevelExecution;
import com.bot.performance.serviceinterface.IApprisalTyeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    AppraisalDetailRepository appraisalDetailRepository;

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

    public List<ObjectiveCatagory> addAppraisalTypeService(AppraisalAndCategoryDTO appraisalAndCategoryDTO) throws Exception {
        FilterModel filterModel = new FilterModel();
        validateApprisalType(appraisalAndCategoryDTO);
        addCategoryAndAppraisalDetail(appraisalAndCategoryDTO);
        filterModel.setSearchString("1=1");
        filterModel.setPageIndex(1);
        filterModel.setPageSize(10);
        return  this.getAppraisalTypeByFilter(filterModel);
    }

    @Transactional(rollbackOn = Exception.class)
    private void addCategoryAndAppraisalDetail(AppraisalAndCategoryDTO appraisalAndCategoryDTO) {
        try {
            java.util.Date utilDate = new java.util.Date();
            var date = new java.sql.Timestamp(utilDate.getTime());
            var lastObjectiveCatagory = apprisalTypeRepository.getLastObjectiveCategory();
            ObjectiveCatagory objectiveCatagory = new ObjectiveCatagory();
            if (lastObjectiveCatagory == null)
                objectiveCatagory.setObjectiveCatagoryId(1);
            else
                objectiveCatagory.setObjectiveCatagoryId(lastObjectiveCatagory.getObjectiveCatagoryId() + 1);

            objectiveCatagory.setObjectiveCatagoryType(appraisalAndCategoryDTO.getObjectiveCatagoryType());
            objectiveCatagory.setTypeDescription(appraisalAndCategoryDTO.getTypeDescription());
            objectiveCatagory.setHikeApproval(appraisalAndCategoryDTO.isHikeApproval());
            objectiveCatagory.setCreatedBy(currentUserDetail.getUserDetail().getUserId());
            objectiveCatagory.setCreatedOn(date);
            objectiveCatagory.setObjectivesId("[]");
            objectiveCatagory.setRolesId(objectMapper.writeValueAsString(appraisalAndCategoryDTO.getRoleIds()));
            apprisalTypeRepository.save(objectiveCatagory);

            AppraisalDetail appraisalDetail = new AppraisalDetail();
            if (appraisalAndCategoryDTO.getAppraisalDetailId() == 0) {
                var lastAppraisalDetail = appraisalDetailRepository.getLastAppraisalDetail();
                if (lastAppraisalDetail == null)
                    appraisalDetail.setAppraisalDetailId(1);
                else
                    appraisalDetail.setAppraisalDetailId(lastAppraisalDetail.getAppraisalDetailId() + 1);
                List<Integer> objectiveIds = new ArrayList<>();
                objectiveIds.add(objectiveCatagory.getObjectiveCatagoryId());
                appraisalDetail.setObjectiveCatagoryId(objectMapper.writeValueAsString(objectiveIds));
                appraisalDetail.setAppraisalCycleStartDate(appraisalAndCategoryDTO.getAppraisalCycleStartDate());
                appraisalDetail.setAppraisalCycleEndDate(appraisalAndCategoryDTO.getAppraisalCycleEndDate());
                appraisalDetail.setIsSelfAppraisal(appraisalAndCategoryDTO.isIsSelfAppraisal());
                appraisalDetail.setIsRaterSelectedByManager(appraisalAndCategoryDTO.isIsRaterSelectedByManager());
                appraisalDetail.setIsRequiredRatersFeedback(appraisalAndCategoryDTO.isIsRequiredRatersFeedback());
                appraisalDetail.setRatersRequired(appraisalAndCategoryDTO.isRatersRequired());
                appraisalDetail.setCanRaterViewAppraisal(appraisalAndCategoryDTO.isCanRaterViewAppraisal());
                appraisalDetail.setMultiraterFeedBackStartDate(appraisalAndCategoryDTO.getMultiraterFeedBackStartDate());
                appraisalDetail.setMultiraterFeedBackEndDate(appraisalAndCategoryDTO.getMultiraterFeedBackEndDate());
                appraisalDetail.setReviewStartDate(appraisalAndCategoryDTO.getReviewStartDate());
                appraisalDetail.setReviewEndDate(appraisalAndCategoryDTO.getReviewEndDate());
                appraisalDetail.setSelfAppraisalStartDate(appraisalAndCategoryDTO.getSelfAppraisalStartDate());
                appraisalDetail.setSelfAppraisalEndDate(appraisalAndCategoryDTO.getSelfAppraisalEndDate());
                appraisalDetail.setSelectionPeriodStartDate(appraisalAndCategoryDTO.getSelectionPeriodStartDate());
                appraisalDetail.setSelectionPeriodEndDate(appraisalAndCategoryDTO.getSelectionPeriodEndDate());
                appraisalDetail.setActiveCycle(false);
            } else  {
                var appraisalDetailData = appraisalDetailRepository.findById(appraisalAndCategoryDTO.getAppraisalDetailId());
                if (appraisalDetailData.isEmpty())
                    throw new Exception("Appraisal detail not found");

                appraisalDetail = appraisalDetailData.get();
                List<Integer> objectCategoryId = objectMapper.readValue(appraisalDetail.getObjectiveCatagoryId(), new TypeReference<List<Integer>>() {});
                objectCategoryId.add(objectiveCatagory.getObjectiveCatagoryId());
                appraisalDetail.setObjectiveCatagoryId(objectMapper.writeValueAsString(objectCategoryId));
            }

            appraisalDetailRepository.save(appraisalDetail);
        } catch (Exception ex) {
            throw  ApplicationException.ThrowBadRequest(ex.getMessage(), ex);
        }
    }

    public List<ObjectiveCatagory> updateAppraisalTypeService(AppraisalAndCategoryDTO appraisalAndCategoryDTO, int objectiveCatagoryId) throws Exception {
        if (objectiveCatagoryId == 0)
            throw new Exception("Invalid appraisal selected");

        validateApprisalType(appraisalAndCategoryDTO);
        updateCtegoryAndAppraisalDeatil(appraisalAndCategoryDTO, objectiveCatagoryId);
        FilterModel filterModel = new FilterModel();
        filterModel.setSearchString("1=1");
        filterModel.setPageSize(10);
        filterModel.setPageIndex(1);
        return this.getAppraisalTypeByFilter(filterModel);
    }
    @Transactional(rollbackOn = Exception.class)
    private void updateCtegoryAndAppraisalDeatil(AppraisalAndCategoryDTO appraisalAndCategoryDTO, int objectiveCatagoryId) {
        try {
            java.util.Date utilDate = new java.util.Date();
            var date = new java.sql.Timestamp(utilDate.getTime());
            var existObjectiveCatagoryData = apprisalTypeRepository.findById(objectiveCatagoryId);
            if (existObjectiveCatagoryData.isEmpty())
                throw new Exception("Objective category not found");

            ObjectiveCatagory existObjectiveCatagory = existObjectiveCatagoryData.get();
            existObjectiveCatagory.setObjectiveCatagoryType(appraisalAndCategoryDTO.getObjectiveCatagoryType());
            existObjectiveCatagory.setHikeApproval(appraisalAndCategoryDTO.isHikeApproval());
            existObjectiveCatagory.setRolesId(objectMapper.writeValueAsString(appraisalAndCategoryDTO.getRoleIds()));
            existObjectiveCatagory.setUpdatedBy(currentUserDetail.getUserDetail().getUserId());
            existObjectiveCatagory.setTypeDescription(appraisalAndCategoryDTO.getTypeDescription());
            existObjectiveCatagory.setUpdatedOn(date);
            apprisalTypeRepository.save(existObjectiveCatagory);

            var existAppraisalDetailData = appraisalDetailRepository.findById(appraisalAndCategoryDTO.getAppraisalDetailId());
            if (existAppraisalDetailData.isEmpty())
                throw new Exception("Appraisal details not found");

            AppraisalDetail existingAppraisalDetail = existAppraisalDetailData.get();
            existingAppraisalDetail.setAppraisalCycleStartDate(appraisalAndCategoryDTO.getAppraisalCycleStartDate());
            existingAppraisalDetail.setAppraisalCycleEndDate((appraisalAndCategoryDTO.getAppraisalCycleEndDate()));
            existingAppraisalDetail.setIsSelfAppraisal(appraisalAndCategoryDTO.isIsSelfAppraisal());
            existingAppraisalDetail.setIsRequiredRatersFeedback(appraisalAndCategoryDTO.isIsRequiredRatersFeedback());
            existingAppraisalDetail.setIsRaterSelectedByManager(appraisalAndCategoryDTO.isIsRaterSelectedByManager());
            existingAppraisalDetail.setRatersRequired(appraisalAndCategoryDTO.isRatersRequired());
            existingAppraisalDetail.setCanRaterViewAppraisal(appraisalAndCategoryDTO.isCanRaterViewAppraisal());
            existingAppraisalDetail.setMultiraterFeedBackStartDate(appraisalAndCategoryDTO.getMultiraterFeedBackStartDate());
            existingAppraisalDetail.setMultiraterFeedBackEndDate(appraisalAndCategoryDTO.getMultiraterFeedBackEndDate());
            existingAppraisalDetail.setSelfAppraisalStartDate(appraisalAndCategoryDTO.getSelfAppraisalStartDate());
            existingAppraisalDetail.setSelfAppraisalEndDate(appraisalAndCategoryDTO.getSelfAppraisalEndDate());
            existingAppraisalDetail.setSelectionPeriodStartDate(appraisalAndCategoryDTO.getSelectionPeriodStartDate());
            existingAppraisalDetail.setSelectionPeriodEndDate(appraisalAndCategoryDTO.getSelectionPeriodEndDate());
            appraisalDetailRepository.save(existingAppraisalDetail);
        } catch (Exception ex) {
            throw ApplicationException.ThrowBadRequest(ex.getMessage(), ex);
        }
    }
    private void validateApprisalType(AppraisalAndCategoryDTO objectiveCatagory) throws Exception {
        if (objectiveCatagory.getObjectiveCatagoryType().isEmpty())
            throw new Exception("Please enter objective category first");

        if (objectiveCatagory.getTypeDescription().isEmpty())
            throw new Exception("Please enter description first");

        if (objectiveCatagory.getReviewStartDate() == null)
            throw new Exception("Please select a valid review from date");

        if (objectiveCatagory.getReviewEndDate() == null)
            throw new Exception("Please select a valid review to date");

        if (objectiveCatagory.isIsRequiredRatersFeedback()) {
            if (objectiveCatagory.getSelectionPeriodStartDate() == null)
                throw new Exception("Please select a valid selection period from date");

            if (objectiveCatagory.getSelectionPeriodEndDate() == null)
                throw new Exception("Please select a valid selection period to date");

            if (objectiveCatagory.getMultiraterFeedBackStartDate() == null)
                throw new Exception("Please select a valid feedback from date");

            if (objectiveCatagory.getMultiraterFeedBackEndDate() == null)
                throw new Exception("Please select a valid feedback to date");
        }

        if (objectiveCatagory.isIsSelfAppraisal()) {
            if (objectiveCatagory.getAppraisalCycleStartDate() == null)
                throw new Exception("Please select a valid appraisal cycle from date");

            if (objectiveCatagory.getAppraisalCycleEndDate() == null)
                throw new Exception("Please select a valid appraisal cycle to date");

            if (objectiveCatagory.getSelfAppraisalStartDate() == null)
                throw new Exception("Please select a valid self appraisal from date");

            if (objectiveCatagory.getSelfAppraisalEndDate() == null)
                throw new Exception("Please select a valid self appraisal to date");
        }

        if (objectiveCatagory.getRoleIds().size() == 0)
            throw new Exception("Please select tag roles");
    }

    @Override
    public String manageAppraisalCycleService(ObjectiveCatagory objectiveCatagory, int objectiveCatagoryId) throws Exception {
        if (objectiveCatagoryId == 0)
            throw new Exception("Invalid appraisal selected");

        // validateApprisalType(objectiveCatagory);
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

    public List<AppraisalAndCategoryDTO> getAppraisalDetailAndCategoryService(int objectiveCategoryId) throws Exception {
        if (objectiveCategoryId == 0)
            throw new Exception("Invalid objective category id");

        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_ObjectiveCatagoryId", objectiveCategoryId, Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_objective_catagory_appraisal_detail_byid", dbParameters);
        List<AppraisalAndCategoryDTO> result =  objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference< List<AppraisalAndCategoryDTO>>() {});
        result.forEach(x -> {
            try {
                x.setRoleIds(objectMapper.readValue(x.getRolesId(), new TypeReference<List<Integer>>() {}));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return  result;
    }

    private Date utcToIstTimeZone(Date date) {
        // Step 1: Parse the UTC time string to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime utcTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Step 2: Convert to IST
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime istTime = utcTime.atZone(ZoneOffset.UTC).withZoneSameInstant(istZone);

        // Step 3: Format the IST time
        String formattedIstTime = istTime.format(formatter);
        Date convertDate = (Date) formatter.parse(formattedIstTime);
        return java.sql.Date.valueOf(formattedIstTime);
    }
}
