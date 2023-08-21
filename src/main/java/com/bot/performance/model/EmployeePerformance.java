package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Table(name = "employee_performance")
@Data
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

    public EmployeePerformance() {}
    public EmployeePerformance(Long employeePerformanceId, Long objectiveId, Long employeeId, int companyId, double currentValue, int status, String comments, String performanceDetail, Long updatedBy, int projectId, Date updatedOn, double targetValue, double rating, int performanceStatus, int appraisalDetailId) {
        this.employeePerformanceId = employeePerformanceId;
        this.objectiveId = objectiveId;
        this.employeeId = employeeId;
        this.companyId = companyId;
        this.currentValue = currentValue;
        this.status = status;
        this.comments = comments;
        this.performanceDetail = performanceDetail;
        this.updatedBy = updatedBy;
        this.projectId = projectId;
        this.updatedOn = updatedOn;
        this.targetValue = targetValue;
        this.rating = rating;
        this.performanceStatus = performanceStatus;
        this.appraisalDetailId = appraisalDetailId;
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
                ", projectId=" + projectId +
                ", updatedOn=" + updatedOn +
                ", targetValue=" + targetValue +
                ", rating=" + rating +
                ", performanceStatus=" + performanceStatus +
                ", appraisalDetailId=" + appraisalDetailId +
                '}';
    }
}
