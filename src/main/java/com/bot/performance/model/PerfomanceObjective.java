package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "performance_objective")
public class PerfomanceObjective {
    @Id
    @Column(name = "ObjectiveId")
    Long objectiveId;

    @Column(name = "Objective")
    String objective;

    @Column(name = "Description")
    String description;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "TimeFrameStart")
    Date timeFrameStart;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "TimeFrmaeEnd")
    Date timeFrmaeEnd;

    @Column(name = "CanManagerSee")
    boolean canManagerSee;

    @Column(name = "ObjectiveTypeId")
    Integer objectiveTypeId;

    @Column(name = "Tag")
    String tag;

    @Column(name = "IsIncludeReview")
    boolean isIncludeReview;

    @Column(name = "ProgressMeassureType")
    int progressMeassureType;

    @Column(name = "StartValue")
    double startValue;

    @Column(name = "TargetValue")
    double targetValue;

    @Column(name = "CompanyId")
    int companyId;

    @Column(name = "UpdatedBy")
    Long updatedBy;

    @Column(name = "UpdatedOn")
    Date updatedOn;

    @Column(name = "CreatedBy")
    Long CreatedBy;

    @Column(name = "CreatedOn")
    Date createdOn;

    @Transient
    double currentValue;

    @Transient
    List<Integer> tagRole;

    @Transient
    Long Total = 0L;

    @Transient
    int status;

    @Transient
    Long employeePerformanceId = 0l;

    @Transient
    List<PerformanceDetail> performanceDetail;

    @Transient
    int financialYear;

    @Transient
    int declarationStartMonth;

    @Transient
    String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(int financialYear) {
        this.financialYear = financialYear;
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

    @Transient
    int declarationEndMonth;

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeFrameStart() {
        return timeFrameStart;
    }

    public void setTimeFrameStart(Date timeFrameStart) {
        this.timeFrameStart = timeFrameStart;
    }

    public Date getTimeFrmaeEnd() {
        return timeFrmaeEnd;
    }

    public void setTimeFrmaeEnd(Date timeFrmaeEnd) {
        this.timeFrmaeEnd = timeFrmaeEnd;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isIncludeReview() {
        return isIncludeReview;
    }

    public void setIncludeReview(boolean includeReview) {
        isIncludeReview = includeReview;
    }

    public int getProgressMeassureType() {
        return progressMeassureType;
    }

    public void setProgressMeassureType(int progressMeassureType) {
        this.progressMeassureType = progressMeassureType;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public List<Integer> getTagRole() {
        return tagRole;
    }

    public void setTagRole(List<Integer> tagRole) {
        this.tagRole = tagRole;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getEmployeePerformanceId() {
        return employeePerformanceId;
    }

    public void setEmployeePerformanceId(Long employeePerformanceId) {
        this.employeePerformanceId = employeePerformanceId;
    }

    public List<PerformanceDetail> getPerformanceDetail() {
        return performanceDetail;
    }

    public void setPerformanceDetail(List<PerformanceDetail> performanceDetail) {
        this.performanceDetail = performanceDetail;
    }

    public Long getTotal() {
        return Total;
    }

    public void setTotal(Long total) {
        Total = total;
    }

    public boolean isCanManagerSee() {
        return canManagerSee;
    }

    public void setCanManagerSee(boolean canManagerSee) {
        this.canManagerSee = canManagerSee;
    }

    public Integer getObjectiveTypeId() {
        return objectiveTypeId;
    }

    public void setObjectiveTypeId(Integer objectiveTypeId) {
        this.objectiveTypeId = objectiveTypeId;
    }

    public PerfomanceObjective() { }

    public PerfomanceObjective(Long objectiveId, String objective, String description, Date timeFrameStart, Date timeFrmaeEnd, boolean canManagerSee, Integer objectiveTypeId, String tag, boolean isIncludeReview, int progressMeassureType, double startValue, double targetValue, int companyId, Long updatedBy, Date updatedOn, Long createdBy, Date createdOn, double currentValue, List<Integer> tagRole, Long total, int status, Long employeePerformanceId, List<PerformanceDetail> performanceDetail, int financialYear, int declarationStartMonth, String comments, int declarationEndMonth) {
        this.objectiveId = objectiveId;
        this.objective = objective;
        this.description = description;
        this.timeFrameStart = timeFrameStart;
        this.timeFrmaeEnd = timeFrmaeEnd;
        this.canManagerSee = canManagerSee;
        this.objectiveTypeId = objectiveTypeId;
        this.tag = tag;
        this.isIncludeReview = isIncludeReview;
        this.progressMeassureType = progressMeassureType;
        this.startValue = startValue;
        this.targetValue = targetValue;
        this.companyId = companyId;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        CreatedBy = createdBy;
        this.createdOn = createdOn;
        this.currentValue = currentValue;
        this.tagRole = tagRole;
        Total = total;
        this.status = status;
        this.employeePerformanceId = employeePerformanceId;
        this.performanceDetail = performanceDetail;
        this.financialYear = financialYear;
        this.declarationStartMonth = declarationStartMonth;
        this.comments = comments;
        this.declarationEndMonth = declarationEndMonth;
    }

    @Override
    public String toString() {
        return "PerfomanceObjective{" +
                "objectiveId=" + objectiveId +
                ", objective='" + objective + '\'' +
                ", description='" + description + '\'' +
                ", timeFrameStart=" + timeFrameStart +
                ", timeFrmaeEnd=" + timeFrmaeEnd +
                ", canManagerSee=" + canManagerSee +
                ", objectiveTypeId=" + objectiveTypeId +
                ", tag='" + tag + '\'' +
                ", isIncludeReview=" + isIncludeReview +
                ", progressMeassureType=" + progressMeassureType +
                ", startValue=" + startValue +
                ", targetValue=" + targetValue +
                ", companyId=" + companyId +
                ", updatedBy=" + updatedBy +
                ", updatedOn=" + updatedOn +
                ", CreatedBy=" + CreatedBy +
                ", createdOn=" + createdOn +
                ", currentValue=" + currentValue +
                ", tagRole=" + tagRole +
                ", Total=" + Total +
                ", status=" + status +
                ", employeePerformanceId=" + employeePerformanceId +
                ", performanceDetail=" + performanceDetail +
                ", financialYear=" + financialYear +
                ", declarationStartMonth=" + declarationStartMonth +
                ", comments='" + comments + '\'' +
                ", declarationEndMonth=" + declarationEndMonth +
                '}';
    }
}
