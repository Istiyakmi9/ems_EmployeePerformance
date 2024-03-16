package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Table(name = "performance_objective")
public class PerfomanceObjective {
    @Id
    @Column(name = "ObjectiveId")
    @JsonProperty("ObjectiveId")
    Long objectiveId;

    @Column(name = "Objective")
    @JsonProperty("Objective")
    String objective;

    @Column(name = "Description")
    @JsonProperty("Description")
    String description;

    @Column(name = "CanManagerSee")
    @JsonProperty("CanManagerSee")
    boolean canManagerSee;

    @Column(name = "IsIncludeReview")
    @JsonProperty("IsIncludeReview")
    boolean isIncludeReview;

    @Column(name = "ProgressMeassureType")
    @JsonProperty("ProgressMeassureType")
    int progressMeassureType;

    @Column(name = "StartValue")
    @JsonProperty("StartValue")
    double startValue;

    @Column(name = "TargetValue")
    @JsonProperty("TargetValue")
    double targetValue;

    @Column(name = "CompanyId")
    @JsonProperty("CompanyId")
    int companyId;

    @Column(name = "UpdatedBy")
    @JsonProperty("UpdatedBy")
    Long updatedBy;

    @Column(name = "UpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("UpdatedOn")
    Date updatedOn;

    @Column(name = "CreatedBy")
    @JsonProperty("CreatedBy")
    Long createdBy;

    @Column(name = "CreatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("CreatedOn")
    Date createdOn;

    @Transient
    @JsonProperty("CurrentValue")
    double currentValue;

    @Transient
    @JsonProperty("TagRole")
    List<Integer> tagRole;

    @Transient
    @JsonProperty("Total")
    Long total;
    @Transient
    @JsonProperty("RowIndex")
    Long rowIndex;

    @Transient
    @JsonProperty("Status")
    int status;

    @Transient
    @JsonProperty("EmployeePerformanceId")
    Long employeePerformanceId;

    @Transient
    @JsonProperty("PerformanceDetail")
    List<PerformanceDetail> performanceDetail;

    @Transient
    @JsonProperty("Comments")
    String comments;
    @Transient
    @JsonProperty("AppraisalDetailId")
    int appraisalDetailId;
    @Transient
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("AppraisalCycleStartDate")
    Date appraisalCycleStartDate;
    @Transient
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("AppraisalCycleEndDate")
    Date appraisalCycleEndDate;
    @Transient
    @JsonProperty("Rating")
    double rating;
    @Transient
    @JsonProperty("PerformanceStatus")
    int performanceStatus;
}
