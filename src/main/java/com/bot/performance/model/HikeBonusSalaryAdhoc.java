package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "hike_bonus_salary_adhoc")
public class HikeBonusSalaryAdhoc {
    @Id
    @Column(name = "SalaryAdhocId")
    @JsonProperty("SalaryAdhocId")
    Long salaryAdhocId;

    @Column(name = "EmployeeId")
    @JsonProperty("EmployeeId")
    Long employeeId;

    @Column(name = "OrganizationId")
    @JsonProperty("OrganizationId")
    Integer organizationId;

    @Column(name = "CompanyId")
    @JsonProperty("CompanyId")
    Integer companyId;

    @Column(name = "IsPaidByCompany")
    @JsonProperty("IsPaidByCompany")
    Boolean isPaidByCompany;

    @Column(name = "IsFine")
    @JsonProperty("IsFine")
    Boolean isFine;

    @Column(name = "IsHikeInSalary")
    @JsonProperty("IsHikeInSalary")
    Boolean isHikeInSalary;

    @Column(name = "IsBonus")
    @JsonProperty("IsBonus")
    Boolean isBonus;

    @Column(name = "Description")
    @JsonProperty("Description")
    String description;

    @Column(name = "Amount")
    @JsonProperty("Amount")
    BigDecimal amount;

    @Column(name = "ApprovedBy")
    @JsonProperty("ApprovedBy")
    Long approvedBy;

    @Column(name = "IsRepeatJob")
    @JsonProperty("IsRepeatJob")
    Boolean isRepeatJob;

    @Column(name = "StartDate")
    @JsonProperty("StartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date startDate;

    @Column(name = "EndDate")
    @JsonProperty("EndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date endDate;

    @Column(name = "IsForSpecificPeriod")
    @JsonProperty("IsForSpecificPeriod")
    Boolean isForSpecificPeriod;

    @Column(name = "SequenceStartDate")
    @JsonProperty("SequenceStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date sequenceStartDate;

    @Column(name = "SequencePeriodOrder")
    @JsonProperty("SequencePeriodOrder")
    Integer sequencePeriodOrder;

    @Column(name = "SequenceEndDate")
    @JsonProperty("SequenceEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date sequenceEndDate;

    @Column(name = "IsActive")
    @JsonProperty("IsActive")
    Boolean isActive;
}
