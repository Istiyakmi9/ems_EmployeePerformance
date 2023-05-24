package com.bot.performance.repository;

import com.bot.performance.model.ApprisalEmployeeDetail;
import com.bot.performance.model.EmployeePerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PerformanceRepository extends JpaRepository<EmployeePerformance, Long> {
    @Query(nativeQuery = true, value = "select e.* from employee_performance e order by e.EmployeePerformanceId desc limit 1")
    EmployeePerformance getLastEmployeePerformance();

    @Query("select p from EmployeePerformance p where p.employeeId = :empId")
    List<EmployeePerformance> getEmpPerformanceByEmpId(@Param("empId") Long empId);

    @Query(value = "Call sp_employee_performance_by_managerid_get(:_ReportingManagerId)", nativeQuery = true)
    List<?> getEmployeeByManagerId(@Param("_ReportingManagerId") Long _ReportingManagerId);
}
