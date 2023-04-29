package com.bot.performance.repository;

import com.bot.performance.model.PerfomanceObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceObjectiveRepository extends JpaRepository<PerfomanceObjective, Long> {


}
