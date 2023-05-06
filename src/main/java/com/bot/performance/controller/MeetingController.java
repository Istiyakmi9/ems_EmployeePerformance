package com.bot.performance.controller;

import com.bot.performance.model.ApiResponse;
import com.bot.performance.model.Meeting;
import com.bot.performance.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eps/meeting/")
public class MeetingController extends BaseController{
    @Autowired
    MeetingService meetingService;

    @PostMapping("manageMeeting")
    public ResponseEntity<ApiResponse> manageMeeting(@RequestBody Meeting meeting) throws Exception {
    var result = meetingService.manageMeetingService(meeting);
    return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getMeetingByEmpId/{employeeId}")
    public  ResponseEntity<ApiResponse> getMeetingByEmpId(@PathVariable Long employeeId) throws Exception {
        var result = meetingService.getMeetingByEmpIdService(employeeId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("updateMeetingStatus/{meetingId}/{status}")
    public  ResponseEntity<ApiResponse> updateMeetingStatus(@PathVariable Long meetingId,
                                                            @PathVariable int status) throws Exception {
        var result = meetingService.updateMeetingStatusService(meetingId, status);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("deleteMeeting/{meetingId}")
    public  ResponseEntity<ApiResponse> deleteMeeting(@PathVariable Long meetingId) throws Exception {
        var result = meetingService.deleteMeetingService(meetingId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
