package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name = "employee_meeting")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Transient
    @JsonProperty( "TalkingPoints")
    String talkingPoints;

    @Column(name = "FilePath")
    @JsonProperty("FilePath")
    String filePath;

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
}
