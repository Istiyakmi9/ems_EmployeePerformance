package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "employee_salary_detail")
@Data
public class EmployeeSalaryDetail {
    @Column(name = "EmployeeId")
    @Id
    @JsonProperty("EmployeeId")
    Long employeeId;
    @Column(name = "CTC")
    @JsonProperty("CTC")
    BigDecimal cTC;
    @Column(name = "GrossIncome")
    @JsonProperty("GrossIncome")
    BigDecimal grossIncome;
    @Column(name = "NetSalary")
    @JsonProperty("NetSalary")
    BigDecimal netSalary;
    @Column(name = "CompleteSalaryDetail")
    @JsonProperty("CompleteSalaryDetail")
    String completeSalaryDetail;
    @Column(name = "NewSalaryDetail")
    @JsonProperty("NewSalaryDetail")
    String newSalaryDetail;
    @Column(name = "GroupId")
    @JsonProperty("GroupId")
    int groupId;
    @Column(name = "TaxDetail")
    @JsonProperty("TaxDetail")
    String taxDetail;
    @Column(name = "FinancialStartYear")
    @JsonProperty("FinancialStartYear")
    int financialStartYear;
    @Column(name = "UpdatedOn")
    @JsonProperty("UpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date updatedOn;
}
