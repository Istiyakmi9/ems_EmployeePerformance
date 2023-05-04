package com.bot.performance.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employee_performance")
public class EmployeePerformance {
    @Id
    @Column(name = "EmployeePerformanceId")
    Long employeePerformanceId;

    @Column(name = "ObjectiveId")
    Long objectiveId;

    @Column(name = "EmployeeId")
    Long employeeId;

    @Column(name = "CompanyId")
    int companyId;

    @Column(name = "CurrentValue")
    double currentValue;

    @Column(name = "Status")
    int status;

    @Column(name = "Comments")
    String comments;

    @Column(name = "PerformanceDetail")
    String performanceDetail;

    @Column(name = "UpdatedBy")
    Long updatedBy;

    @Column(name = "UpdatedOn")
    Date updatedOn;

    @Transient
    double targetValue;

    public Long getEmployeePerformanceId() {
        return employeePerformanceId;
    }

    public void setEmployeePerformanceId(Long employeePerformanceId) {
        this.employeePerformanceId = employeePerformanceId;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPerformanceDetail() {
        return performanceDetail;
    }

    public void setPerformanceDetail(String performanceDetail) {
        this.performanceDetail = performanceDetail;
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

    @Override
    public String toString() {
        return "EmployeePerformance{" +
                "employeePerformanceId=" + employeePerformanceId +
                ", objectiveId=" + objectiveId +
                ", employeeId=" + employeeId +
                ", companyId=" + companyId +
                ", currentValue=" + currentValue +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                ", performanceDetail='" + performanceDetail + '\'' +
                ", updatedBy=" + updatedBy +
                ", updatedOn=" + updatedOn +
                '}';
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public EmployeePerformance() {}

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public EmployeePerformance(Long employeePerformanceId, Long objectiveId, Long employeeId, int companyId, double currentValue, int status, String comments, String performanceDetail, Long updatedBy, Date updatedOn) {
        this.employeePerformanceId = employeePerformanceId;
        this.objectiveId = objectiveId;
        this.employeeId = employeeId;
        this.companyId = companyId;
        this.currentValue = currentValue;
        this.status = status;
        this.comments = comments;
        this.performanceDetail = performanceDetail;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }
}
