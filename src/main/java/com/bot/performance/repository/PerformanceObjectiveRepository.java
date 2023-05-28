package com.bot.performance.repository;

import com.bot.performance.model.EmployeePerformance;
import com.bot.performance.model.Meeting;
import com.bot.performance.model.PerfomanceObjective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface PerformanceObjectiveRepository extends JpaRepository<PerfomanceObjective, Long> {
    @Query(nativeQuery = true, value = "select e.* from performance_objective e order by e.ObjectiveId desc limit 1")
    PerfomanceObjective getLastPerformanceObjective();

    @Query(value = "call sp_objective_get_by_employee(:_EmployeeId)", nativeQuery = true)
    ArrayList<PerfomanceObjective> getObjectivesByEmployeeId(@Param("_EmployeeId") Long _EmployeeId);
}
