package com.bot.performance.repository;

import com.bot.performance.model.Meeting;
import com.bot.performance.model.PerfomanceObjective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceObjectiveRepository extends JpaRepository<PerfomanceObjective, Long> {
    @Query("select p FROM PerfomanceObjective p WHERE UPPER(p.objective) LIKE CONCAT('%', UPPER(?1), '%')" + " OR p.targetValue LIKE CONCAT('%', ?1 , '%')")
    Page<PerfomanceObjective> findByFilter(String searchString, Pageable paging);

    @Query(nativeQuery = true, value = "select e.* from performance_objective e order by e.ObjectiveId desc limit 1")
    PerfomanceObjective getLastPerformanceObjective();
}
