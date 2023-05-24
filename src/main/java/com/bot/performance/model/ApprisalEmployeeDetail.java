package com.bot.performance.model;

public class ApprisalEmployeeDetail {
    public long employeePerformanceId;
    public long objectiveId;
    public String firstName;
    public String lastName;
    public String mobile;
    public String email;

    public long getEmployeePerformanceId() {
        return employeePerformanceId;
    }

    public void setEmployeePerformanceId(long employeePerformanceId) {
        this.employeePerformanceId = employeePerformanceId;
    }

    public long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
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
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getPerformanceDetail() {
        return performanceDetail;
    }

    public void setPerformanceDetail(String performanceDetail) {
        this.performanceDetail = performanceDetail;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long employeeId;
    public int companyId;
    public double currentValue;
    public int status;
    public String Comments;
    public String performanceDetail;
    public long updatedBy;
}
