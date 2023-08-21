package com.bot.performance.repository;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeetingReppository {
    @Autowired
    DbManager dbManager;

    public void deleteMeetingByIdRepository(long meetingId) {
        dbManager.execute("delete from employee_meeting where MeetingId = " + meetingId);
    }

    public List<Meeting> getMeetingByEmpIdRepository(long meetingId) {
        return dbManager.queryList("select * from employee_meeting where CreatedBy = " + meetingId, Meeting.class);
    }
}
