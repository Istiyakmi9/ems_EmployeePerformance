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
    int AppraisalDetailId;
    @Column(name = "ObjectiveCatagoryId")
    String ObjectiveCatagoryId;
    @Column(name = "AppraisalCycleStartDate")
    Date AppraisalCycleStartDate;
    @Column(name = "AppraisalCycleEndDate")
    Date AppraisalCycleEndDate;
    @Column(name = "StartedBy")
    long StartedBy;
    @Column(name = "StartedOn")
    Date StartedOn;
    @Column(name = "IsSelfAppraisal")
    boolean IsSelfAppraisal;
    @Column(name = "IsRequiredRatersFeedback")
    boolean IsRequiredRatersFeedback;
    @Column(name = "IsRaterSelectedByManager")
    boolean IsRaterSelectedByManager;
    @Column(name = "RatersRequired")
    boolean RatersRequired;
    @Column(name = "CanRaterViewAppraisal")
    boolean CanRaterViewAppraisal;
    @Column(name = "MultiraterFeedBackStartDate")
    Date MultiraterFeedBackStartDate;
    @Column(name = "MultiraterFeedBackEndDate")
    Date MultiraterFeedBackEndDate;
    @Column(name = "ReviewStartDate")
    Date ReviewStartDate;
    @Column(name = "ReviewEndDate")
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
