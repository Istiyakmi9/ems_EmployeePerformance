package com.bot.performance.service;

import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.Meeting;
import com.bot.performance.repository.MeetingReppository;
import com.bot.performance.serviceinterface.IMeetingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.bot.performance.model.ApplicationConstant.*;
import java.util.List;
import java.util.Optional;
@Service
public class MeetingService implements IMeetingService {
    @Autowired
    MeetingReppository meetingReppository;
    @Autowired
    CurrentSession currentSession;
    @Autowired
    ObjectMapper objectMapper;

    public List<Meeting> getMeetingByEmpIdService(Long employeeId) throws Exception {
        if (employeeId <= 0)
            throw new Exception("Invalid employee selected. Please login again");

        return meetingReppository.getMeetingByEmpId(employeeId);
    }

    public List<Meeting> manageMeetingService(Meeting meeting) throws Exception {
        validateMeeting(meeting);
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());

        Meeting existMeeting = new Meeting();
        Optional<Meeting> existMeetingData = meetingReppository.findById(meeting.getMeetingId());
        if (existMeetingData.isEmpty()) {
            existMeeting = meeting;
            var lastMeeting = meetingReppository.getLastEmployeeMeeting();
            if (lastMeeting == null)
                existMeeting.setMeetingId(1L);
            else
                existMeeting.setMeetingId(lastMeeting.getMeetingId()+1);

            existMeeting.setCreatedOn(date);
            existMeeting.setCreatedBy(currentSession.getUserDetail().getUserId());
        } else  {
            existMeeting = existMeetingData.get();
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

        var result = meetingReppository.save(existMeeting);
        if (result == null)
            throw new Exception("Fail to insert/update meeting details");

        return this.getMeetingByEmpIdService(currentSession.getUserDetail().getUserId());
    }

    public List<Meeting> updateMeetingStatusService(Long meetingId, int status) throws Exception {
        if (meetingId <= 0)
            throw new Exception("Invalid meeting selected. Please select a valid email");

        var meetingData = meetingReppository.findById(meetingId);
        if (meetingData.isEmpty())
            throw new Exception("Meeting records not found");

        var meeting = meetingData.get();
        if (status == Completed)
            meeting.setStatus(Completed);
        else if (status == Canceled)
            meeting.setStatus(Canceled);
        else
            throw new Exception("Invalid status selected");

        var result = meetingReppository.save(meeting);
        if (result == null)
            throw new Exception("Fail to insert/update meeting details");

        return this.getMeetingByEmpIdService(currentSession.getUserDetail().getUserId());
    }

    public List<Meeting> deleteMeetingService(Long meetingId) throws Exception {
        if (meetingId <= 0)
            throw new Exception("Invalid meeting selected. Please select a valid email");

        meetingReppository.deleteById(meetingId);
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
