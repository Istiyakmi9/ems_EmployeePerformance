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
public class AppraisalReviewDetailDTO {
    @JsonProperty("EmployeeId")
    @Id
    Long employeeId;
    @JsonProperty("PromotedDesignation")
    int promotedDesignation;
    @JsonProperty("HikePercentage")
    BigDecimal hikePercentage;
    @JsonProperty("HikeAmount")
    BigDecimal hikeAmount;
    @JsonProperty("EstimatedSalary")
    BigDecimal estimatedSalary;
    @JsonProperty("CompanyId")
    int companyId;
    @JsonProperty("AppraisalDetailId")
    int appraisalDetailId;
    @JsonProperty("ProjectId")
    int projectId;
    @JsonProperty("AppraisalReviewId")
    long appraisalReviewId;
    @JsonProperty("PreviousSalary")
    BigDecimal previousSalary;
    @JsonProperty("AppraisalCycleStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date appraisalCycleStartDate;
    @JsonProperty("Comments")
    String comments;
    @JsonProperty("Rating")
    BigDecimal rating;
    @Transient
    @JsonProperty("FirstName")
    String firstName;
    @Transient
    @JsonProperty("LastName")
    String lastName;

}
