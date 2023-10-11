package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
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
    @Column(name = "EmployeeId")
    @Id
    Long employeeId;
    @Column(name = "PromotedDesignation")
    int promotedDesignation;
    @Column(name = "HikePercentage")
    BigDecimal hikePercentage;
    @Column(name = "HikeAmount")
    BigDecimal hikeAmount;
    @Column(name = "EstimatedSalary")
    BigDecimal estimatedSalary;
    @Column(name = "CompanyId")
    int companyId;
    @Column(name = "AppraisalDetailId")
    int appraisalDetailId;
    @Column(name = "ProjectId")
    int projectId;
    @Column(name = "AppraisalReviewId")
    long appraisalReviewId;
    @Column(name = "PreviousSalary")
    BigDecimal previousSalary;
    @Column(name = "AppraisalCycleStartDate")
    Date appraisalCycleStartDate;
    @Column(name = "Comments")
    String comments;
    @Column(name = "Rating")
    BigDecimal rating;
}
