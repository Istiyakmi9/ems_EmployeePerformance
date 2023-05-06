package com.bot.performance.repository;

import com.bot.performance.model.CompanySetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanySettingRepository extends JpaRepository<CompanySetting, Long> {
}
