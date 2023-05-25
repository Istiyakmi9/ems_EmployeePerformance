package com.bot.performance.model;

import java.util.Date;

public class ObjectiveDetail extends PerfomanceObjective {
    int objectiveCatagoryId;
    String objectiveCatagoryType;
    boolean isTagByDepartment;
    boolean isTagByRole;
    Date fromDate;
    Date toDate;

    public int getObjectiveCatagoryId() {
        return objectiveCatagoryId;
    }

    public void setObjectiveCatagoryId(int objectiveCatagoryId) {
        this.objectiveCatagoryId = objectiveCatagoryId;
    }

    public String getObjectiveCatagoryType() {
        return objectiveCatagoryType;
    }

    public void setObjectiveCatagoryType(String objectiveCatagoryType) {
        this.objectiveCatagoryType = objectiveCatagoryType;
    }

    public boolean isTagByDepartment() {
        return isTagByDepartment;
    }

    public void setTagByDepartment(boolean tagByDepartment) {
        isTagByDepartment = tagByDepartment;
    }

    public boolean isTagByRole() {
        return isTagByRole;
    }

    public void setTagByRole(boolean tagByRole) {
        isTagByRole = tagByRole;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
