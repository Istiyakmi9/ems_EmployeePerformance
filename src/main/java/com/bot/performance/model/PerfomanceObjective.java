package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
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

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "TimeFrameStart")
    @JsonProperty("TimeFrameStart")
    Date timeFrameStart;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "TimeFrmaeEnd")
    @JsonProperty("TimeFrmaeEnd")
    Date timeFrmaeEnd;

    @Column(name = "CanManagerSee")
    @JsonProperty("CanManagerSee")
    boolean canManagerSee;

    @Column(name = "ObjectiveTypeId")
    @JsonProperty("ObjectiveTypeId")
    Integer objectiveTypeId;

    @Column(name = "Tag")
    @JsonProperty("Tag")
    String tag;

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
    @JsonProperty("Index")
    Long index;

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
    @JsonProperty("FinancialYear")
    int financialYear;

    @Transient
    @JsonProperty("DeclarationStartMonth")
    int declarationStartMonth;

    @Transient
    @JsonProperty("Comments")
    String comments;
    @Transient
    @JsonProperty("DeclarationEndMonth")
    int declarationEndMonth;
}
