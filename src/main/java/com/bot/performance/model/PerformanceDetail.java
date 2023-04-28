package com.bot.performance.model;

import java.util.Date;

public class PerformanceDetail {
    int Index;
    double CurrentValue;
    int Status;
    String Comments;
    Date UpdatedOn;

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public double getCurrentValue() {
        return CurrentValue;
    }

    public void setCurrentValue(double currentValue) {
        CurrentValue = currentValue;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public Date getUpdatedOn() {
        return UpdatedOn;
    }

    public PerformanceDetail(int index, double currentValue, int status, String comments, Date updatedOn) {
        Index = index;
        CurrentValue = currentValue;
        Status = status;
        Comments = comments;
        UpdatedOn = updatedOn;
    }

    public PerformanceDetail() {}

    @Override
    public String toString() {
        return "PerformanceDetail{" +
                "Index=" + Index +
                ", CurrentValue=" + CurrentValue +
                ", Status=" + Status +
                ", Comments='" + Comments + '\'' +
                ", UpdatedOn=" + UpdatedOn +
                '}';
    }

    public void setUpdatedOn(Date updatedOn) {
        UpdatedOn = updatedOn;
    }
}
