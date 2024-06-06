package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

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

    @Column(name = "ProcessStepId")
    @JsonProperty("ProcessStepId")
    int processStepId;

    @Column(name = "FinancialYear")
    @JsonProperty("FinancialYear")
    int financialYear;

    @Column(name = "OrganizationId")
    @JsonProperty("OrganizationId")
    Integer organizationId;

    @Column(name = "CompanyId")
    @JsonProperty("CompanyId")
    Integer companyId;

    @Column(name = "IsPaidByCompany")
    @JsonProperty("IsPaidByCompany")
    Boolean isPaidByCompany;

    @Column(name = "IsPaidByEmployee")
    @JsonProperty("IsPaidByEmployee")
    Boolean isPaidByEmployee;

    @Column(name = "IsFine")
    @JsonProperty("IsFine")
    Boolean isFine;

    @Column(name = "IsHikeInSalary")
    @JsonProperty("IsHikeInSalary")
    Boolean isHikeInSalary;

    @Column(name = "IsBonus")
    @JsonProperty("IsBonus")
    Boolean isBonus;

    @Column(name = "IsReimbursment")
    @JsonProperty("IsReimbursment")
    Boolean isReimbursment;

    @Column(name = "IsSalaryOnHold")
    @JsonProperty("IsSalaryOnHold")
    Boolean isSalaryOnHold;

    @Column(name = "IsArrear")
    @JsonProperty("IsArrear")
    Boolean isArrear;

    @Column(name = "IsOvertime")
    @JsonProperty("IsOvertime")
    Boolean isOvertime;

    @Column(name = "IsCompOff")
    @JsonProperty("IsCompOff")
    Boolean isCompOff;

    @Column(name = "OTCalculatedOn")
    @JsonProperty("OTCalculatedOn")
    String oTCalculatedOn;

    @Column(name = "Amount")
    @JsonProperty("Amount")
    BigDecimal amount;

    @Column(name = "AmountInPercentage")
    @JsonProperty("AmountInPercentage")
    BigDecimal amountInPercentage;

    @Column(name = "IsActive")
    @JsonProperty("IsActive")
    Boolean isActive;

    @Column(name = "PaymentActionType")
    @JsonProperty("PaymentActionType")
    String paymentActionType;

    @Column(name = "Comments")
    @JsonProperty("Comments")
    String comments;

    @Column(name = "Status")
    @JsonProperty("Status")
    Integer status;

    @Column(name = "ForYear")
    @JsonProperty("ForYear")
    Integer forYear;

    @Column(name = "ForMonth")
    @JsonProperty("ForMonth")
    Integer forMonth;

    @Column(name = "ProgressState")
    @JsonProperty("ProgressState")
    Integer progressState;
}
