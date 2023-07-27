package com.bot.performance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "employee_salary_detail")
@Data
public class EmployeeSalaryDetail {
    @Column(name = "EmployeeId")
    @Id
    Long employeeId;
    @Column(name = "CTC")
    BigDecimal cTC;
    @Column(name = "GrossIncome")
    BigDecimal grossIncome;
    @Column(name = "NetSalary")
    BigDecimal netSalary;
    @Column(name = "CompleteSalaryDetail")
    String completeSalaryDetail;
    @Column(name = "NewSalaryDetail")
    String newSalaryDetail;
    @Column(name = "GroupId")
    int groupId;
    @Column(name = "TaxDetail")
    String taxDetail;
    @Column(name = "FinancialStartYear")
    int financialStartYear;
    @Column(name = "UpdatedOn")
    Date UpdatedOn;
}
