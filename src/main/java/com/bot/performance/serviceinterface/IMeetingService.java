package com.bot.performance.serviceinterface;

import com.bot.performance.model.Meeting;

import java.util.List;

public interface IMeetingService {
    List<Meeting> manageMeetingService(Meeting meeting) throws Exception;
    List<Meeting> getMeetingByEmpIdService(Long employeeId) throws Exception;
    List<Meeting> updateMeetingStatusService(Long meetingId, int status) throws Exception;
    List<Meeting> deleteMeetingService(Long meetingId) throws Exception;
}
