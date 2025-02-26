package com.bot.performance.repository;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.model.AppProperties;
import com.bot.performance.model.Meeting;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Repository
public class MeetingRepository {
    private final AppProperties _appProperties;
    @Autowired
    DbManager dbManager;
    @Autowired
    ObjectMapper objectMapper;

    public MeetingRepository(AppProperties appProperties) {
        _appProperties = appProperties;
    }

    public void deleteMeetingByIdRepository(long meetingId) {
        dbManager.execute("delete from employee_meeting where MeetingId = " + meetingId);
    }

    public List<Meeting> getMeetingByEmpIdRepository(long meetingId) {
        var meetings = dbManager.queryList("select * from employee_meeting where CreatedBy = " + meetingId, Meeting.class);
        if (meetings.size() > 0) {
            meetings = objectMapper.convertValue(meetings, new TypeReference<List<Meeting>>() {});
            for (var meeting: meetings) {
                if (meeting.getFilePath() != null && !meeting.getFilePath().equals("")) {
                    try {
                        meeting.setTalkingPoints(readTextFileContent(meeting.getFilePath()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return meetings;
    }

    private String readTextFileContent(String filePath) throws Exception {
        if (filePath.isEmpty() || filePath.isBlank())
            throw new Exception("Invalid file path");

        var url = _appProperties.getResourceBaseUrl() + filePath.replace("\\", "/");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        client.send(request, HttpResponse.BodyHandlers.ofInputStream()).body()))) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            return content.toString();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
