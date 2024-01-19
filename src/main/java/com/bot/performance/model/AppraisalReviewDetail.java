package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appraisal_review_detail")
public class AppraisalReviewDetail {
    @Column(name = "AppraisalReviewId")
    @JsonProperty("AppraisalReviewId")
    @Id
    long appraisalReviewId;
    @Column(name = "EmployeeId")
    @JsonProperty("EmployeeId")
    Long employeeId;
    @Column(name = "PromotedDesignation")
    @JsonProperty("PromotedDesignation")
    int promotedDesignation;
    @Column(name = "HikePercentage")
    @JsonProperty("HikePercentage")
    BigDecimal hikePercentage;
    @Column(name = "HikeAmount")
    @JsonProperty("HikeAmount")
    BigDecimal hikeAmount;
    @Column(name = "EstimatedSalary")
    @JsonProperty("EstimatedSalary")
    BigDecimal estimatedSalary;
    @Column(name = "CompanyId")
    @JsonProperty("CompanyId")
    int companyId;
    @Column(name = "AppraisalDetailId")
    @JsonProperty("AppraisalDetailId")
    int appraisalDetailId;
    @Column(name = "ProjectId")
    @JsonProperty("ProjectId")
    int projectId;
    @Column(name = "PreviousSalary")
    @JsonProperty("PreviousSalary")
    BigDecimal previousSalary;
    @Column(name = "AppraisalCycleStartDate")
    @JsonProperty("AppraisalCycleStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date appraisalCycleStartDate;
    @Column(name = "Comments")
    @JsonProperty("Comments")
    String comments;
    @Column(name = "Rating")
    @JsonProperty("Rating")
    BigDecimal rating;
    @Transient
    @JsonProperty("FirstName")
    String firstName;
    @Transient
    @JsonProperty("LastName")
    String lastName;
    @Transient
    @JsonProperty("DesignationId")
    int designationId;
    @Transient
    @JsonProperty("ObjectiveCategoryId")
    int objectiveCategoryId;
    @Column(name = "AppraisalEffectedDate")
    @JsonProperty("AppraisalEffectedDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date appraisalEffectedDate;
    @Transient
    @JsonProperty("AppraisalStatus")
    int appraisalStatus;
    @Column(name = "IsActive")
    @JsonProperty("IsActive")
    boolean isActive;
    @Column(name = "Status")
    @JsonProperty("Status")
    int status;
    @Transient
    @JsonProperty("ObjectiveStatus")
    int objectiveStatus;
}
