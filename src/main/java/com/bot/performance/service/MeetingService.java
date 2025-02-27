package com.bot.performance.service;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.model.*;
import com.bot.performance.repository.MeetingRepository;
import com.bot.performance.serviceinterface.IMeetingService;
import com.bot.performance.util.MicroserviceRequest;
import com.bot.performance.util.RequestMicroservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.bot.performance.model.ApplicationConstant.*;
@Service
public class MeetingService implements IMeetingService {
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    CurrentSession currentSession;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    DbManager dbManager;
    @Autowired
    RequestMicroservice requestMicroservice;

    public List<Meeting> getMeetingByEmpIdService(Long employeeId) throws Exception {
        if (employeeId <= 0)
            throw new Exception("Invalid employee selected. Please login again");

        return meetingRepository.getMeetingByEmpIdRepository(employeeId);
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

        String documentPath = Paths.get(currentSession.getCompanyCode(), "oneonemeeting").toString();
        String filename = existMeeting.getMeetingTitle().replaceAll("\\s+", "");
        filename = filename.substring(0, Math.min(filename.length(), 15)) + "_" + existMeeting.getMeetingId() + ".txt";
        String oldFileName = null;
        if (existMeeting.getFilePath() != null && !existMeeting.getFilePath().isEmpty())
            oldFileName = Path.of(existMeeting.getFilePath()).getFileName().toString();

        TextFileFolderDetail textFileFolderDetail = TextFileFolderDetail.builder()
                .textDetail(meeting.getTalkingPoints())
                .oldFileName(oldFileName)
                .serviceName(ApplicationConstant.EmstumFileService)
                .folderPath(documentPath)
                .fileName(filename)
                .build();

        var file = saveTextFile(textFileFolderDetail);
        var filePath = Paths.get(file.getFilePath(), file.getFileName()).toString();

        existMeeting.setFilePath(replaceEscapeCharacter(filePath));
        existMeeting.setOneToOneEmpMeeting(objectMapper.writeValueAsString(meeting.getEmployeesMeeting()));
        if (existMeeting.getMeetingDate().compareTo(date) < 0)
            existMeeting.setStatus(Pending);
        else            existMeeting.setStatus(NotSubmitted);

        dbManager.save(existMeeting);
        return this.getMeetingByEmpIdService(currentSession.getUserDetail().getUserId());
    }

    private Files saveTextFile(TextFileFolderDetail textFileFolderDetail) throws Exception {
        MicroserviceRequest microserviceRequest = MicroserviceRequest.builder()
                .token(currentSession.getAuthorization())
                .connectionString(currentSession.getLocalConnectionString())
                .companyCode(currentSession.getCompanyCode())
                .build();
        return requestMicroservice.saveTextFile(microserviceRequest, textFileFolderDetail);
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

        meetingRepository.deleteMeetingByIdRepository(meetingId);
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

    private String replaceEscapeCharacter(String filePath) {
        return  filePath.replace("\\", "\\\\");
    }
}
