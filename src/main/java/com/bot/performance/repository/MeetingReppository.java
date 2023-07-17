package com.bot.performance.repository;

import com.bot.performance.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories()
public interface MeetingReppository extends JpaRepository<Meeting, Long> {
    @Query(nativeQuery = true, value = "select e.* from employee_meeting e order by e.MeetingId desc limit 1")
    Meeting getLastEmployeeMeeting();

    @Query(value = "select m from Meeting m where m.createdBy = :employeeId")
    List<Meeting> getMeetingByEmpId(@Param("employeeId") Long employeeId);
}
