package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
