package com.bot.performance.model;

import java.util.Date;

public class ObjectiveDetail extends PerfomanceObjective {
    int objectiveCatagoryId;
    String objectiveCatagoryType;
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
