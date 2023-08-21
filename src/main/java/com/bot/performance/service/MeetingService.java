package com.bot.performance.service;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.Meeting;
import com.bot.performance.repository.MeetingReppository;
import com.bot.performance.serviceinterface.IMeetingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.bot.performance.model.ApplicationConstant.*;
import java.util.List;
@Service
public class MeetingService implements IMeetingService {
    @Autowired
    MeetingReppository meetingReppository;
    @Autowired
    CurrentSession currentSession;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    DbManager dbManager;

    public List<Meeting> getMeetingByEmpIdService(Long employeeId) throws Exception {
        if (employeeId <= 0)
            throw new Exception("Invalid employee selected. Please login again");

        return meetingReppository.getMeetingByEmpIdRepository(employeeId);
    }

    public List<Meeting> manageMeetingService(Meeting meeting) throws Exception {
        validateMeeting(meeting);
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());

        Meeting existMeeting = dbManager.getById(meeting.getMeetingId(), Meeting.class);
        if (existMeeting == null) {
            existMeeting = meeting;
            existMeeting.setMeetingId(dbManager.nextLongPrimaryKey(Meeting.class));
            existMeeting.setCreatedOn(date);
            existMeeting.setCreatedBy(currentSession.getUserDetail().getUserId());
        } else  {
            existMeeting.setMeetingDate(meeting.getMeetingDate());
            existMeeting.setMeetingFrequency(meeting.getMeetingFrequency());
            existMeeting.setMeetingPlaforms(meeting.getMeetingPlaforms());
            existMeeting.setMeetingTitle(meeting.getMeetingTitle());
            existMeeting.setEndTime(meeting.getEndTime());
            existMeeting.setStartTime(meeting.getStartTime());
            existMeeting.setUpdatedOn(date);
            existMeeting.setUpdatedBy(currentSession.getUserDetail().getUserId());
        }

        existMeeting.setTalkingPoints(objectMapper.writeValueAsString(meeting.getTalkingPoints()));
        existMeeting.setOneToOneEmpMeeting(objectMapper.writeValueAsString(meeting.getEmployeesMeeting()));
        if (existMeeting.getMeetingDate().compareTo(date) < 0)
            existMeeting.setStatus(Pending);
        else
            existMeeting.setStatus(NotSubmitted);

        dbManager.save(existMeeting);
        return this.getMeetingByEmpIdService(currentSession.getUserDetail().getUserId());
    }

    public List<Meeting> updateMeetingStatusService(Long meetingId, int status) throws Exception {
        if (meetingId <= 0)
            throw new Exception("Invalid meeting selected. Please select a valid email");

        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        var meeting = dbManager.getById(meetingId, Meeting.class);
        if (meeting == null)
            throw new Exception("Meeting records not found");

        if (status == Completed)
            meeting.setStatus(Completed);
        else if (status == Canceled)
            meeting.setStatus(Canceled);
        else
            throw new Exception("Invalid status selected");

        meeting.setUpdatedBy(currentSession.getUserDetail().getUserId());
        meeting.setUpdatedOn(date);
        dbManager.save(meeting);

        return this.getMeetingByEmpIdService(currentSession.getUserDetail().getUserId());
    }

    public List<Meeting> deleteMeetingService(Long meetingId) throws Exception {
        if (meetingId <= 0)
            throw new Exception("Invalid meeting selected. Please select a valid email");

        meetingReppository.deleteMeetingByIdRepository(meetingId);
        return this.getMeetingByEmpIdService(currentSession.getUserDetail().getUserId());
    }

    private void validateMeeting(Meeting meeting) throws Exception {
        if (meeting.getMeetingTitle() == null)
            throw new Exception("Please enter meeting title");

        if (meeting.getTalkingPoints() == null)
            throw new Exception("Please enter meeting points");

        if (meeting.getMeetingDate() == null)
            throw new Exception("Please enter a valid meeting date");

        if (meeting.getStartTime() == null)
            throw new Exception("Please enter meeting start time");

        if (meeting.getEndTime() == null)
            throw new Exception("Please enter meeting end time");

        if (meeting.getMeetingFrequency() == 0)
            throw new Exception("Please select meeting frequency");

        if (meeting.getEmployeesMeeting().size() <= 0)
            throw new Exception("Please employee to have a 1:1 meeting");
    }
}
