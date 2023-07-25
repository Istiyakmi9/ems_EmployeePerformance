package com.bot.performance.repository;

import com.bot.performance.model.EmployeeSalaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSalaryDetailRepository extends JpaRepository<EmployeeSalaryDetail, Long> {
}
