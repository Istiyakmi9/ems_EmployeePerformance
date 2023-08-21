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
    int AppraisalDetailId;
    @Column(name = "ObjectiveCatagoryId")
    @JsonProperty("ObjectiveCatagoryId")
    String ObjectiveCatagoryId;
    @Column(name = "AppraisalCycleStartDate")
    @JsonProperty("AppraisalCycleStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date AppraisalCycleStartDate;
    @Column(name = "AppraisalCycleEndDate")
    @JsonProperty("AppraisalCycleEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date AppraisalCycleEndDate;
    @Column(name = "StartedBy")
    @JsonProperty("StartedBy")
    long StartedBy;
    @Column(name = "StartedOn")
    @JsonProperty("StartedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date StartedOn;
    @Column(name = "IsSelfAppraisal")
    @JsonProperty("IsSelfAppraisal")
    boolean IsSelfAppraisal;
    @Column(name = "IsRequiredRatersFeedback")
    @JsonProperty("IsRequiredRatersFeedback")
    boolean IsRequiredRatersFeedback;
    @Column(name = "IsRaterSelectedByManager")
    @JsonProperty("IsRaterSelectedByManager")
    boolean IsRaterSelectedByManager;
    @Column(name = "RatersRequired")
    @JsonProperty("RatersRequired")
    boolean RatersRequired;
    @Column(name = "CanRaterViewAppraisal")
    @JsonProperty("CanRaterViewAppraisal")
    boolean CanRaterViewAppraisal;
    @Column(name = "MultiraterFeedBackStartDate")
    @JsonProperty("MultiraterFeedBackStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date MultiraterFeedBackStartDate;
    @Column(name = "MultiraterFeedBackEndDate")
    @JsonProperty("MultiraterFeedBackEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date MultiraterFeedBackEndDate;
    @Column(name = "ReviewStartDate")
    @JsonProperty("ReviewStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date ReviewStartDate;
    @Column(name = "ReviewEndDate")
    @JsonProperty("ReviewEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date ReviewEndDate;
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
