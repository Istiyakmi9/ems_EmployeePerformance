package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "appraisal_detail")
public class AppraisalDetail {
    @Id
    @Column(name = "AppraisalDetailId")
    @JsonProperty("AppraisalDetailId")
    int appraisalDetailId;
    @Column(name = "ObjectiveCatagoryId")
    @JsonProperty("ObjectiveCatagoryId")
    String objectiveCatagoryId;
    @Column(name = "AppraisalName")
    @JsonProperty("AppraisalName")
    String appraisalName;
    @Column(name = "AppraisalCycleStartDate")
    @JsonProperty("AppraisalCycleStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date appraisalCycleStartDate;
    @Column(name = "AppraisalCycleEndDate")
    @JsonProperty("AppraisalCycleEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date appraisalCycleEndDate;
    @Column(name = "StartedBy")
    @JsonProperty("StartedBy")
    long startedBy;
    @Column(name = "StartedOn")
    @JsonProperty("StartedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date startedOn;
    @Column(name = "IsSelfAppraisal")
    @JsonProperty("IsSelfAppraisal")
    boolean isSelfAppraisal;
    @Column(name = "IsRequiredRatersFeedback")
    @JsonProperty("IsRequiredRatersFeedback")
    boolean isRequiredRatersFeedback;
    @Column(name = "IsRaterSelectedByManager")
    @JsonProperty("IsRaterSelectedByManager")
    boolean isRaterSelectedByManager;
    @Column(name = "RatersRequired")
    @JsonProperty("RatersRequired")
    boolean ratersRequired;
    @Column(name = "CanRaterViewAppraisal")
    @JsonProperty("CanRaterViewAppraisal")
    boolean canRaterViewAppraisal;
    @Column(name = "MultiraterFeedBackStartDate")
    @JsonProperty("MultiraterFeedBackStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date multiraterFeedBackStartDate;
    @Column(name = "MultiraterFeedBackEndDate")
    @JsonProperty("MultiraterFeedBackEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date multiraterFeedBackEndDate;
    @Column(name = "ReviewStartDate")
    @JsonProperty("ReviewStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date reviewStartDate;
    @Column(name = "ReviewEndDate")
    @JsonProperty("ReviewEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date reviewEndDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelfAppraisalStartDate")
    @Column(name = "SelfAppraisalStartDate")
    public Date selfAppraisalStartDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelfAppraisalEndDate")
    @Column(name = "SelfAppraisalEndDate")
    public Date selfAppraisalEndDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelectionPeriodStartDate")
    @Column(name = "SelectionPeriodStartDate")
    public Date selectionPeriodStartDate;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelectionPeriodEndDate")
    @Column(name = "SelectionPeriodEndDate")
    public Date selectionPeriodEndDate;
    @Column(name = "IsActiveCycle")
    @JsonProperty("IsActiveCycle")
    boolean isActiveCycle;
}
