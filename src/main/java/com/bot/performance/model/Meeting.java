package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

@Table(name = "employee_meeting")
public class Meeting {
    @Id
    @Column(name = "MeetingId")
    Long meetingId;

    @Column(name = "StartTime")
    String startTime;

    @Column(name = "EndTime")
    String endTime;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "MeetingDate")
    Date meetingDate;

    @Transient
    List<Integer> employeesMeeting;

    @Column (name ="OneToOneEmpMeeting")
    String oneToOneEmpMeeting;

    @Column(name = "MeetingTitle")
    String meetingTitle;

    @Column(name = "meetingPlaforms")
    int meetingPlaforms;

    @Column(name = "MeetingFrequency")
    int meetingFrequency;

    @Column(name = "TalkingPoints")
    String talkingPoints;

    @Column(name = "UpdatedBy")
    Long updatedBy;

    @Column(name = "Status")
    int status;

    @Column(name = "UpdatedOn")
    Date updatedOn;

    @Column(name = "CreatedBy")
    Long createdBy;

    @Column(name = "CreatedOn")
    Date createdOn;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public int getMeetingPlaforms() {
        return meetingPlaforms;
    }

    public void setMeetingPlaforms(int meetingPlaforms) {
        this.meetingPlaforms = meetingPlaforms;
    }

    public int getMeetingFrequency() {
        return meetingFrequency;
    }

    public void setMeetingFrequency(int meetingFrequency) {
        this.meetingFrequency = meetingFrequency;
    }

    public String getTalkingPoints() {
        return talkingPoints;
    }

    public void setTalkingPoints(String talkingPoints) {
        this.talkingPoints = talkingPoints;
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


    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Meeting() {}

    public List<Integer> getEmployeesMeeting() {
        return employeesMeeting;
    }

    public void setEmployeesMeeting(List<Integer> employeesMeeting) {
        this.employeesMeeting = employeesMeeting;
    }

    public String getOneToOneEmpMeeting() {
        return oneToOneEmpMeeting;
    }

    public void setOneToOneEmpMeeting(String oneToOneEmpMeeting) {
        this.oneToOneEmpMeeting = oneToOneEmpMeeting;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Meeting(Long meetingId, String startTime, String endTime, Date meetingDate, List<Integer> employeesMeeting, String oneToOneEmpMeeting, String meetingTitle, int meetingPlaforms, int meetingFrequency, String talkingPoints, Long updatedBy, int status, Date updatedOn, Long createdBy, Date createdOn) {
        this.meetingId = meetingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingDate = meetingDate;
        this.employeesMeeting = employeesMeeting;
        this.oneToOneEmpMeeting = oneToOneEmpMeeting;
        this.meetingTitle = meetingTitle;
        this.meetingPlaforms = meetingPlaforms;
        this.meetingFrequency = meetingFrequency;
        this.talkingPoints = talkingPoints;
        this.updatedBy = updatedBy;
        this.status = status;
        this.updatedOn = updatedOn;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", meetingDate=" + meetingDate +
                ", employeesMeeting=" + employeesMeeting +
                ", oneToOneEmpMeeting='" + oneToOneEmpMeeting + '\'' +
                ", meetingTitle='" + meetingTitle + '\'' +
                ", meetingPlaforms=" + meetingPlaforms +
                ", meetingFrequency=" + meetingFrequency +
                ", talkingPoints='" + talkingPoints + '\'' +
                ", updatedBy=" + updatedBy +
                ", status=" + status +
                ", updatedOn=" + updatedOn +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                '}';
    }
}
