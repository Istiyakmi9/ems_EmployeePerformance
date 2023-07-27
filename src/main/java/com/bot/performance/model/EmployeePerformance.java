package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employee_performance")
public class EmployeePerformance {
    @Id
    @Column(name = "EmployeePerformanceId")
    @JsonProperty("EmployeePerformanceId")
    Long employeePerformanceId;

    @Column(name = "ObjectiveId")
    @JsonProperty("ObjectiveId")
    Long objectiveId;

    @Column(name = "EmployeeId")
    @JsonProperty("EmployeeId")
    Long employeeId;

    @Column(name = "CompanyId")
    @JsonProperty("CompanyId")
    int companyId;

    @Column(name = "CurrentValue")
    @JsonProperty("CurrentValue")
    double currentValue = 0;

    @Column(name = "Status")
    @JsonProperty("Status")
    int status;

    @Column(name = "Comments")
    @JsonProperty("Comments")
    String comments;

    @Column(name = "PerformanceDetail")
    @JsonProperty("PerformanceDetail")
    String performanceDetail;

    @Column(name = "UpdatedBy")
    @JsonProperty("UpdatedBy")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Long updatedBy;

    @Column(name = "ProjectId")
    @JsonProperty("ProjectId")
    int projectId;

    @Column(name = "UpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("UpdatedOn")
    Date updatedOn;

    @Transient
    @JsonProperty("TargetValue")
    double targetValue;
    @Column(name = "Rating")
    @JsonProperty("Rating")
    double rating;
    @Column(name = "PerformanceStatus")
    @JsonProperty("PerformanceStatus")
    int performanceStatus;
    @Column(name = "AppraisalDetailId")
    @JsonProperty("AppraisalDetailId")
    int appraisalDetailId;

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

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPerformanceStatus() {
        return performanceStatus;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getAppraisalDetailId() {
        return appraisalDetailId;
    }

    public void setAppraisalDetailId(int appraisalDetailId) {
        this.appraisalDetailId = appraisalDetailId;
    }

    public void setPerformanceStatus(int performanceStatus) {
        this.performanceStatus = performanceStatus;
    }
}
