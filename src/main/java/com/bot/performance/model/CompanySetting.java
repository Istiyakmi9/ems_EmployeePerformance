package com.bot.performance.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "company_setting")
public class CompanySetting {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "SettingId")
    Long settingId;

    @Column(name = "CompanyId")
    int companyId;

    @Column(name = "ProbationPeriodInDays")
    int probationPeriodInDays;

    @Column(name = "NoticePeriodInDays")
    int noticePeriodInDays;

    @Column(name = "declarationStartMonth")
    int declarationStartMonth;

    @Column(name = "DeclarationEndMonth")
    int declarationEndMonth;

    @Column(name = "FinancialYear")
    int financialYear;

    @Column(name = "IsPrimary")
    boolean isPrimary;

    @Column(name = "AttendanceSubmissionLimit")
    int attendanceSubmissionLimit;

    @Column(name = "TimezoneName")
    String timezoneName;

    @Column(name = "LeaveAccrualRunCronDayOfMonth")
    int leaveAccrualRunCronDayOfMonth;

    @Column(name = "EveryMonthLastDayOfDeclaration")
    int everyMonthLastDayOfDeclaration;

    @Column(name = "UpdatedBy")
    Long updatedBy;

    @Column(name = "UpdatedOn")
    Date updatedOn;

    @Column(name = "CreatedBy")
    Long CreatedBy;

    @Column(name = "CreatedOn")
    Date createdOn;

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getProbationPeriodInDays() {
        return probationPeriodInDays;
    }

    public void setProbationPeriodInDays(int probationPeriodInDays) {
        this.probationPeriodInDays = probationPeriodInDays;
    }

    public int getNoticePeriodInDays() {
        return noticePeriodInDays;
    }

    public void setNoticePeriodInDays(int noticePeriodInDays) {
        this.noticePeriodInDays = noticePeriodInDays;
    }

    public int getDeclarationStartMonth() {
        return declarationStartMonth;
    }

    public void setDeclarationStartMonth(int declarationStartMonth) {
        this.declarationStartMonth = declarationStartMonth;
    }

    public int getDeclarationEndMonth() {
        return declarationEndMonth;
    }

    public void setDeclarationEndMonth(int declarationEndMonth) {
        this.declarationEndMonth = declarationEndMonth;
    }

    public int getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(int financialYear) {
        this.financialYear = financialYear;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public int getAttendanceSubmissionLimit() {
        return attendanceSubmissionLimit;
    }

    public void setAttendanceSubmissionLimit(int attendanceSubmissionLimit) {
        this.attendanceSubmissionLimit = attendanceSubmissionLimit;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public int getLeaveAccrualRunCronDayOfMonth() {
        return leaveAccrualRunCronDayOfMonth;
    }

    public void setLeaveAccrualRunCronDayOfMonth(int leaveAccrualRunCronDayOfMonth) {
        this.leaveAccrualRunCronDayOfMonth = leaveAccrualRunCronDayOfMonth;
    }

    public int getEveryMonthLastDayOfDeclaration() {
        return everyMonthLastDayOfDeclaration;
    }

    public void setEveryMonthLastDayOfDeclaration(int everyMonthLastDayOfDeclaration) {
        this.everyMonthLastDayOfDeclaration = everyMonthLastDayOfDeclaration;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        CreatedBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public CompanySetting() {}

    public CompanySetting(Long settingId, int companyId, int probationPeriodInDays, int noticePeriodInDays, int declarationStartMonth, int declarationEndMonth, int financialYear, boolean isPrimary, int attendanceSubmissionLimit, String timezoneName, int leaveAccrualRunCronDayOfMonth, int everyMonthLastDayOfDeclaration, Long updatedBy, Date updatedOn, Long createdBy, Date createdOn) {
        this.settingId = settingId;
        this.companyId = companyId;
        this.probationPeriodInDays = probationPeriodInDays;
        this.noticePeriodInDays = noticePeriodInDays;
        this.declarationStartMonth = declarationStartMonth;
        this.declarationEndMonth = declarationEndMonth;
        this.financialYear = financialYear;
        this.isPrimary = isPrimary;
        this.attendanceSubmissionLimit = attendanceSubmissionLimit;
        this.timezoneName = timezoneName;
        this.leaveAccrualRunCronDayOfMonth = leaveAccrualRunCronDayOfMonth;
        this.everyMonthLastDayOfDeclaration = everyMonthLastDayOfDeclaration;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        CreatedBy = createdBy;
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "CompanySetting{" +
                "settingId=" + settingId +
                ", companyId=" + companyId +
                ", probationPeriodInDays=" + probationPeriodInDays +
                ", noticePeriodInDays=" + noticePeriodInDays +
                ", declarationStartMonth=" + declarationStartMonth +
                ", declarationEndMonth=" + declarationEndMonth +
                ", financialYear=" + financialYear +
                ", isPrimary=" + isPrimary +
                ", attendanceSubmissionLimit=" + attendanceSubmissionLimit +
                ", timezoneName='" + timezoneName + '\'' +
                ", leaveAccrualRunCronDayOfMonth=" + leaveAccrualRunCronDayOfMonth +
                ", everyMonthLastDayOfDeclaration=" + everyMonthLastDayOfDeclaration +
                ", updatedBy=" + updatedBy +
                ", updatedOn=" + updatedOn +
                ", CreatedBy=" + CreatedBy +
                ", createdOn=" + createdOn +
                '}';
    }
}
