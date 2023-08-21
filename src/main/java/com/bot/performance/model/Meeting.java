package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@Table(name = "employee_meeting")
public class Meeting {
    @Id
    @Column(name = "MeetingId")
    @JsonProperty( "MeetingId")
    Long meetingId;

    @Column(name = "StartTime")
    @JsonProperty( "StartTime")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    String startTime;

    @Column(name = "EndTime")
    @JsonProperty( "EndTime")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    String endTime;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "MeetingDate")
    @JsonProperty( "MeetingDate")
    Date meetingDate;

    @Transient
    @JsonProperty( "EmployeesMeeting")
    List<Integer> employeesMeeting;

    @Column (name ="OneToOneEmpMeeting")
    @JsonProperty ("OneToOneEmpMeeting")
    String oneToOneEmpMeeting;

    @Column(name = "MeetingTitle")
    @JsonProperty( "MeetingTitle")
    String meetingTitle;

    @Column(name = "meetingPlaforms")
    @JsonProperty( "meetingPlaforms")
    int meetingPlaforms;

    @Column(name = "MeetingFrequency")
    @JsonProperty( "MeetingFrequency")
    int meetingFrequency;

    @Column(name = "TalkingPoints")
    @JsonProperty( "TalkingPoints")
    String talkingPoints;

    @Column(name = "UpdatedBy")
    @JsonProperty( "UpdatedBy")
    Long updatedBy;

    @Column(name = "Status")
    @JsonProperty( "Status")
    int status;

    @Column(name = "UpdatedOn")
    @JsonProperty( "UpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date updatedOn;

    @Column(name = "CreatedBy")
    @JsonProperty( "CreatedBy")
    Long createdBy;

    @Column(name = "CreatedOn")
    @JsonProperty( "CreatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
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
