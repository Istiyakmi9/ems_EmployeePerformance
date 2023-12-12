package com.bot.performance.service;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.db.utils.LowLevelExecution;
import com.bot.performance.model.*;
import com.bot.performance.repository.AppraisalDetailRepository;
import com.bot.performance.repository.PromotionAndHikeRepository;
import com.bot.performance.serviceinterface.IPromotionAndHikeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

@Service
public class PromotionAndHikeService implements IPromotionAndHikeService {
    @Autowired
    PromotionAndHikeRepository promotionAndHikeRepository;
    @Autowired
    AppraisalDetailRepository appraisalDetailRepository;
    @Autowired
    DbManager dbManager;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CurrentSession currentUserDetail;
    @Autowired
    LowLevelExecution lowLevelExecution;

    @Transactional
    public List<AppraisalReviewDetail> addPromotionAndHike(List<AppraisalReviewDetail> appraisalReview) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        long appraisalReviewId = dbManager.nextLongPrimaryKey(AppraisalReviewDetail.class) - 1;
        var activeAppraisalDetails = appraisalDetailRepository.getActiveAppraisalDetailRepository();
        if (activeAppraisalDetails == null)
            throw new Exception("Appraisal detail not found");

        List<AppraisalReviewFinalizerStatus> appraisalReviewFinalizer = new ArrayList<>();
        int AppraisalFinalizer = dbManager.nextIntPrimaryKey(AppraisalReviewFinalizerStatus.class) - 1;
        List<AppraisalReviewDetail> appraisalReviewDetails = new ArrayList<>();

        for (var promotionDetail : appraisalReview) {
            if (promotionDetail.getEmployeeId() == 0)
                throw new Exception("Invalid employee selected");

            var appraisalReviewDetail = appraisalDetailRepository.getAppraisalReviewDetailRepository(promotionDetail.getAppraisalReviewId());
            var employeeSalaryDetail = dbManager.getById(promotionDetail.getEmployeeId(), EmployeeSalaryDetail.class);
            if (appraisalReviewDetail == null) {
                appraisalReviewDetail = promotionDetail;
                appraisalReviewId = appraisalReviewId + 1;
                appraisalReviewDetail.setAppraisalDetailId(activeAppraisalDetails.getAppraisalDetailId());
                validateHikeDetail(promotionDetail, employeeSalaryDetail);
                appraisalReviewDetail.setAppraisalCycleStartDate(activeAppraisalDetails.getAppraisalCycleStartDate());
                appraisalReviewDetail.setAppraisalReviewId(appraisalReviewId);
                appraisalReviewDetail.setPreviousSalary(employeeSalaryDetail.getCTC());
            } else  {
                appraisalReviewDetail.setEstimatedSalary(promotionDetail.getEstimatedSalary());
                appraisalReviewDetail.setHikePercentage(promotionDetail.getHikePercentage());
                appraisalReviewDetail.setHikeAmount(promotionDetail.getHikeAmount());
                appraisalReviewDetail.setComments(promotionDetail.getComments());
                appraisalReviewDetail.setRating(promotionDetail.getRating());
                appraisalReviewDetail.setPromotedDesignation(promotionDetail.getPromotedDesignation());
            }
            appraisalReviewDetails.add(appraisalReviewDetail);

            var existingappraisalReviewFinalizer = appraisalDetailRepository.getAppraisalReviewFinalizerRepository(appraisalReviewDetail.getAppraisalReviewId());
            if (existingappraisalReviewFinalizer.size()==0) {
                var data = getAppraisalLevel(appraisalReviewDetail.getProjectId(), appraisalReviewDetail.getEmployeeId(),
                        appraisalReviewDetail.getObjectiveCategoryId(), appraisalReviewDetail.getCompanyId());
                long finalAppraisalReviewId = appraisalReviewId;

                int i = 0;
                while (i < data.size()) {
                    var reviewerDetail = new AppraisalReviewFinalizerStatus();
                    AppraisalFinalizer = AppraisalFinalizer + 1;

                    reviewerDetail.setAppraisalFinalizer(AppraisalFinalizer);
                    reviewerDetail.setAppraisalReviewId(finalAppraisalReviewId);
                    reviewerDetail.setReviwerId(data.get(i).getEmployeeId());
                    reviewerDetail.setEmail(data.get(i).getEmail());
                    reviewerDetail.setFullName(data.get(i).getFullName());
                    reviewerDetail.setActionRequired(!data.get(i).isOptional());
                    if (i + 1 == 1) {
                        reviewerDetail.setStatus(ApplicationConstant.Approved);
                        reviewerDetail.setReactedOn(utilDate);
                    } else if (i + 1 == 2) {
                        reviewerDetail.setStatus(ApplicationConstant.Pending);
                    } else {
                        reviewerDetail.setStatus(ApplicationConstant.NotSubmitted);
                    }

                    reviewerDetail.setApprovalLevel(i + 1);
                    if (data.size() == 1)
                        manageHikeSalaryService(appraisalReviewDetail);

                    appraisalReviewFinalizer.add(reviewerDetail);
                    i++;
                }
            } else {
                var currentApprailsalReview = existingappraisalReviewFinalizer.stream()
                        .filter(x -> x.getReviwerId() == currentUserDetail.getUserDetail().getUserId()).toList().get(0);
                var previousAppraisalReview = existingappraisalReviewFinalizer.stream()
                        .filter(x -> x.getApprovalLevel() == currentApprailsalReview.getApprovalLevel()-1).toList().get(0);
                if (previousAppraisalReview != null) {
                    if (previousAppraisalReview.isActionRequired() && previousAppraisalReview.getStatus() != ApplicationConstant.Approved) {
                        throw new Exception(String.format("%s is not approved the appraisal", previousAppraisalReview.getFullName()));
                    }
                }
                currentApprailsalReview.setStatus(ApplicationConstant.Approved);
                currentApprailsalReview.setReactedOn(utilDate);

                var nextAppraisalReview = existingappraisalReviewFinalizer.stream()
                        .filter(x -> x.getApprovalLevel() == currentApprailsalReview.getApprovalLevel()+1).toList();
                if (nextAppraisalReview.size() > 0)
                    nextAppraisalReview.get(0).setStatus(ApplicationConstant.Pending);
                else
                    manageHikeSalaryService(appraisalReviewDetail);

                appraisalReviewFinalizer.addAll(existingappraisalReviewFinalizer);
            }
        }
        dbManager.saveAll(appraisalReviewDetails, AppraisalReviewDetail.class);
        dbManager.saveAll(appraisalReviewFinalizer, AppraisalReviewFinalizerStatus.class);
        return appraisalReviewDetails;
    }


    private List<ApprovalChainDetail> getAppraisalLevel(int projectId, long employeeId, int objectiveCategoryId, int companyId) throws Exception {
        if (companyId == 0)
            throw new Exception("Invalid company id");

        if (objectiveCategoryId == 0)
            throw new Exception("Invalid objective category");

        if (employeeId == 0)
            throw new Exception("Invalid employee id");

        if (projectId == 0)
            throw new Exception("Invalid projectId");

        return promotionAndHikeRepository.getAppraisalChainLevelRepository(objectiveCategoryId, employeeId, companyId, projectId);
    }

    private void validateHikeDetail(AppraisalReviewDetail appraisalReviewDetail, EmployeeSalaryDetail employeeSalaryDetail) throws Exception {
        BigDecimal proposedHikeAmount = (employeeSalaryDetail.getCTC().multiply(appraisalReviewDetail.getHikePercentage()))
                .divide(new BigDecimal(100));
        proposedHikeAmount = proposedHikeAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        if (!proposedHikeAmount.equals(appraisalReviewDetail.getHikeAmount().setScale(2, BigDecimal.ROUND_HALF_EVEN)))
            throw new Exception("Proposed hike amount calculation mismatched");

        if (appraisalReviewDetail.getEstimatedSalary().subtract(employeeSalaryDetail.getCTC()).signum() < 0)
            throw new Exception("Expected CTC is invalid");

        if (appraisalReviewDetail.getHikePercentage().signum() < 0)
            throw new Exception("Hike percentage is invalid");

        if (appraisalReviewDetail.getHikeAmount().signum() < 0)
            throw new Exception("Hike amount is invalid");

        if (appraisalReviewDetail.getEmployeeId() <= 0)
            throw new Exception("Employee detail not found");
    }

    public List<AppraisalReviewDetailDTO> getPromotionAndHikeService(long employeeId) throws Exception {
        if (employeeId <= 0)
            throw new Exception("Invalid employee id");

        return promotionAndHikeRepository.getPromotionAndHikeRepository(employeeId);
    }

    public List<TeamMemberAndAppraisalFinalizer> getApprovePromotionAndHikeService() throws Exception {
        return promotionAndHikeRepository.getApprovePromotionAndHikeRepository();
    }

    public String reOpenAppraisalObjectiveService(Long userId, List<Integer> reviewIds) throws Exception {
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid employee id passed");
        }

        if (reviewIds == null || reviewIds.isEmpty()) {
            throw new IllegalArgumentException("Appraisal review ids are not value");
        }

        String status = "fail";
        try {
            String ids = objectMapper.writeValueAsString(reviewIds);

            Map<String, Object> result = lowLevelExecution.executeProcedure("sp_appraisal_review_detail_reopen_review", List.of(
                    new DbParameters("_EmployeeId", userId, Types.BIGINT),
                    new DbParameters("_AppraisalReviewIds", ids, Types.VARCHAR)
            ));

            if (result == null) {
                throw new RuntimeException("Fail to update the record(s)");
            }

            if (result.containsKey("_ProcessingResult")) {
                status = result.get("_ProcessingResult").toString();
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return status;
    }

    public String reOpenEmployeeObjectiveService(Long employeeId, int appraisalDetailId) throws Exception {
        if (employeeId <= 0)
            throw new IllegalArgumentException("Invalid employee id passed");

        if (appraisalDetailId <= 0)
            throw new IllegalArgumentException("Invalid appraisal detail id passed");

        String status = "fail";
        try {
            Map<String, Object> result = lowLevelExecution.executeProcedure("sp_employee_performance_reopen", List.of(
                    new DbParameters("_EmployeeId", employeeId, Types.BIGINT),
                    new DbParameters("_AppraisalDetailId", appraisalDetailId, Types.INTEGER)
            ));

            if (result == null)
                throw new RuntimeException("Fail to update the record(s)");

            if (result.containsKey("_ProcessingResult"))
                status = result.get("_ProcessingResult").toString();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return status;
    }
    @Transactional
    public List<AppraisalReviewFinalizerStatus> approveAppraisalReviewDetailService(List<AppraisalReviewDetailDTO> appraisalReviewDetailDTOS) throws Exception {
        return manageAppraisalReviewDetailService(appraisalReviewDetailDTOS, ApplicationConstant.Approved);
    }
    @Transactional
    public List<AppraisalReviewFinalizerStatus> rejectAppraisalReviewDetailService(List<AppraisalReviewDetailDTO> appraisalReviewDetailDTOS) throws Exception {
        return manageAppraisalReviewDetailService(appraisalReviewDetailDTOS, ApplicationConstant.Rejected);
    }

    private List<AppraisalReviewFinalizerStatus> manageAppraisalReviewDetailService(List<AppraisalReviewDetailDTO> appraisalReviewDetailDTOS, int status) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        List<AppraisalReviewFinalizerStatus> appraisalReviewFinalizers = new ArrayList<>();

        for (var appraisalDetail : appraisalReviewDetailDTOS) {
            if (appraisalDetail.getEmployeeId() == 0)
                throw new Exception("Invalid employee selected");

            if (appraisalDetail.getAppraisalReviewId() == 0)
                throw new Exception("Invalid appraisal");

            var result = appraisalDetailRepository.getAppraisalFinalizerReviewRepository(appraisalDetail.getAppraisalReviewId());
            result = objectMapper.convertValue(result, new TypeReference<List<AppraisalReviewFinalizerStatus>>() {});
            var finalizerReview = result.stream().filter(x -> x.getReviwerId() == currentUserDetail.getUserDetail().getUserId()).toList();
            if (finalizerReview.get(0).getStatus() == ApplicationConstant.NotSubmitted ||
                finalizerReview.get(0).getStatus() == ApplicationConstant.Pending) {
                finalizerReview.get(0).setStatus(status);
                finalizerReview.get(0).setReactedOn(utilDate);
            }

            var nextFinalizerReview = result.stream().filter(x -> x.getApprovalLevel() == finalizerReview.get(0).getApprovalLevel()+1).toList();
            if (nextFinalizerReview.size() > 0 && nextFinalizerReview.get(0).getStatus() == ApplicationConstant.NotSubmitted) {
                nextFinalizerReview.get(0).setStatus(ApplicationConstant.Pending);
            }
            appraisalReviewFinalizers.addAll(result);
        }
        dbManager.saveAll(appraisalReviewFinalizers, AppraisalReviewFinalizerStatus.class);
        return appraisalReviewFinalizers;
    }

    @Transactional
    private void manageHikeSalaryService(AppraisalReviewDetail appraisalReviewDetails) throws Exception {
        if (appraisalReviewDetails != null) {
            long hikeBonusSalaryAdhocId = dbManager.nextLongPrimaryKey(HikeBonusSalaryAdhoc.class);
            HikeBonusSalaryAdhoc hikeBonusSalaryAdhoc = new HikeBonusSalaryAdhoc();
            hikeBonusSalaryAdhoc.setSalaryAdhocId(hikeBonusSalaryAdhocId);
            hikeBonusSalaryAdhoc.setEmployeeId(appraisalReviewDetails.getEmployeeId());
            hikeBonusSalaryAdhoc.setOrganizationId(currentUserDetail.getUserDetail().getOrganizationId());
            hikeBonusSalaryAdhoc.setCompanyId(currentUserDetail.getUserDetail().getCompanyId());
            hikeBonusSalaryAdhoc.setIsPaidByCompany(true);
            hikeBonusSalaryAdhoc.setIsFine(false);
            hikeBonusSalaryAdhoc.setIsHikeInSalary(true);
            hikeBonusSalaryAdhoc.setIsBonus(false);
            hikeBonusSalaryAdhoc.setDescription(null);
            hikeBonusSalaryAdhoc.setAmount(appraisalReviewDetails.getHikeAmount());
            hikeBonusSalaryAdhoc.setApprovedBy(currentUserDetail.getUserDetail().getUserId());
            hikeBonusSalaryAdhoc.setStartDate(nextMonthDate(true));
            hikeBonusSalaryAdhoc.setEndDate(nextMonthDate(false));
            hikeBonusSalaryAdhoc.setIsForSpecificPeriod(false);
            hikeBonusSalaryAdhoc.setSequenceStartDate(null);
            hikeBonusSalaryAdhoc.setSequenceEndDate(null);
            hikeBonusSalaryAdhoc.setSequencePeriodOrder(0);
            hikeBonusSalaryAdhoc.setIsActive(true);
            hikeBonusSalaryAdhoc.setIsRepeatJob(false);
            dbManager.save(hikeBonusSalaryAdhoc);
        }
    }
    private Date nextMonthDate(Boolean isNextMonthStartDate) {
        Date currentDate = new Date();

        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Move to the next month
        calendar.add(Calendar.MONTH, 1);

        // Set the day of the month to the first day
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Get the first day of the next month
        Date nextMonthStartDate = calendar.getTime();
        if (isNextMonthStartDate)
            return nextMonthStartDate;

        // Move to the last day of the next month
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        // Get the last day of the next month
        return calendar.getTime();
    }
}
