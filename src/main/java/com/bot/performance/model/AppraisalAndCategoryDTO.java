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
    @JsonProperty("AppraisalName")
    public String appraisalName;
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
    @JsonProperty("ApprovalWorkflowId")
    int approvalWorkflowId;
}
