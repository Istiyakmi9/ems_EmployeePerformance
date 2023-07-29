package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AppraisalAndCategoryDTO {
    @JsonProperty("ObjectiveCatagoryId")
    int objectiveCatagoryId;
    @JsonProperty("ObjectiveCatagoryType")
    public String objectiveCatagoryType;
    @JsonProperty("TypeDescription")
    public String typeDescription;
    @JsonProperty("RolesId")
    public String rolesId;
    @JsonProperty("ObjectivesId")
    public String objectivesId;
    @JsonProperty("IsHikeApproval")
    boolean isHikeApproval;
    @JsonProperty("CreatedBy")
    public Long createdBy;
    @JsonProperty("UpdatedBy")
    public Long updatedBy;
    @JsonProperty("CreatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    public Date createdOn;
    @JsonProperty("UpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    public Date updatedOn;
    @JsonProperty("Status")
    public String status;
    @JsonProperty("Total")
    int total;
    @JsonProperty("Index")
    int index;
    @JsonProperty("ObjectiveIds")
    List<Integer> objectiveIds;
    @JsonProperty("RoleIds")
    List<Integer> roleIds;
    @JsonProperty("AppraisalDetailId")
    int AppraisalDetailId;
    @JsonProperty("AppraisalCycleStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date AppraisalCycleStartDate;
    @JsonProperty("AppraisalCycleEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date AppraisalCycleEndDate;
    @JsonProperty("StartedBy")
    long StartedBy;
    @JsonProperty("StartedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date StartedOn;
    @JsonProperty("IsSelfAppraisal")
    boolean IsSelfAppraisal;
    @JsonProperty("IsRequiredRatersFeedback")
    boolean IsRequiredRatersFeedback;
    @JsonProperty("IsRaterSelectedByManager")
    boolean IsRaterSelectedByManager;
    @JsonProperty("RatersRequired")
    boolean RatersRequired;
    @JsonProperty("CanRaterViewAppraisal")
    boolean CanRaterViewAppraisal;
    @JsonProperty("MultiraterFeedBackStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date MultiraterFeedBackStartDate;
    @JsonProperty("MultiraterFeedBackEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date MultiraterFeedBackEndDate;
    @JsonProperty("ReviewStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date ReviewStartDate;
    @JsonProperty("ReviewEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date ReviewEndDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelfAppraisalStartDate")
    public Date selfAppraisalStartDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelfAppraisalEndDate")
    public Date selfAppraisalEndDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelectionPeriodStartDate")
    public Date selectionPeriodStartDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelectionPeriodEndDate")
    public Date selectionPeriodEndDate;
    public AppraisalAndCategoryDTO() {

    }
    public AppraisalAndCategoryDTO(int objectiveCatagoryId, String objectiveCatagoryType, String typeDescription, String rolesId, String objectivesId, boolean isHikeApproval, Long createdBy, Long updatedBy, Date createdOn, Date updatedOn, String status, int total, int index, List<Integer> objectiveIds, List<Integer> roleIds, int appraisalDetailId, Date appraisalCycleStartDate, Date appraisalCycleEndDate, long startedBy, Date startedOn, boolean isSelfAppraisal, boolean isRequiredRatersFeedback, boolean isRaterSelectedByManager, boolean ratersRequired, boolean canRaterViewAppraisal, Date multiraterFeedBackStartDate, Date multiraterFeedBackEndDate, Date reviewStartDate, Date reviewEndDate, Date selfAppraisalStartDate, Date selfAppraisalEndDate, Date selectionPeriodStartDate, Date selectionPeriodEndDate) {
        this.objectiveCatagoryId = objectiveCatagoryId;
        this.objectiveCatagoryType = objectiveCatagoryType;
        this.typeDescription = typeDescription;
        this.rolesId = rolesId;
        this.objectivesId = objectivesId;
        this.isHikeApproval = isHikeApproval;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.status = status;
        this.total = total;
        this.index = index;
        this.objectiveIds = objectiveIds;
        this.roleIds = roleIds;
        AppraisalDetailId = appraisalDetailId;
        AppraisalCycleStartDate = appraisalCycleStartDate;
        AppraisalCycleEndDate = appraisalCycleEndDate;
        StartedBy = startedBy;
        StartedOn = startedOn;
        IsSelfAppraisal = isSelfAppraisal;
        IsRequiredRatersFeedback = isRequiredRatersFeedback;
        IsRaterSelectedByManager = isRaterSelectedByManager;
        RatersRequired = ratersRequired;
        CanRaterViewAppraisal = canRaterViewAppraisal;
        MultiraterFeedBackStartDate = multiraterFeedBackStartDate;
        MultiraterFeedBackEndDate = multiraterFeedBackEndDate;
        ReviewStartDate = reviewStartDate;
        ReviewEndDate = reviewEndDate;
        this.selfAppraisalStartDate = selfAppraisalStartDate;
        this.selfAppraisalEndDate = selfAppraisalEndDate;
        this.selectionPeriodStartDate = selectionPeriodStartDate;
        this.selectionPeriodEndDate = selectionPeriodEndDate;
    }

    @Override
    public String toString() {
        return "AppraisalAndCategoryDTO{" +
                "objectiveCatagoryId=" + objectiveCatagoryId +
                ", objectiveCatagoryType='" + objectiveCatagoryType + '\'' +
                ", typeDescription='" + typeDescription + '\'' +
                ", rolesId='" + rolesId + '\'' +
                ", objectivesId='" + objectivesId + '\'' +
                ", isHikeApproval=" + isHikeApproval +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", status='" + status + '\'' +
                ", total=" + total +
                ", index=" + index +
                ", objectiveIds=" + objectiveIds +
                ", roleIds=" + roleIds +
                ", AppraisalDetailId=" + AppraisalDetailId +
                ", AppraisalCycleStartDate=" + AppraisalCycleStartDate +
                ", AppraisalCycleEndDate=" + AppraisalCycleEndDate +
                ", StartedBy=" + StartedBy +
                ", StartedOn=" + StartedOn +
                ", IsSelfAppraisal=" + IsSelfAppraisal +
                ", IsRequiredRatersFeedback=" + IsRequiredRatersFeedback +
                ", IsRaterSelectedByManager=" + IsRaterSelectedByManager +
                ", RatersRequired=" + RatersRequired +
                ", CanRaterViewAppraisal=" + CanRaterViewAppraisal +
                ", MultiraterFeedBackStartDate=" + MultiraterFeedBackStartDate +
                ", MultiraterFeedBackEndDate=" + MultiraterFeedBackEndDate +
                ", ReviewStartDate=" + ReviewStartDate +
                ", ReviewEndDate=" + ReviewEndDate +
                ", selfAppraisalStartDate=" + selfAppraisalStartDate +
                ", selfAppraisalEndDate=" + selfAppraisalEndDate +
                ", selectionPeriodStartDate=" + selectionPeriodStartDate +
                ", selectionPeriodEndDate=" + selectionPeriodEndDate +
                '}';
    }
}
