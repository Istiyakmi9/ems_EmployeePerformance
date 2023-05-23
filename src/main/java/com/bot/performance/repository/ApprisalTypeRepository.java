package com.bot.performance.repository;

import com.bot.performance.model.ObjectiveCatagory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprisalTypeRepository extends JpaRepository<ObjectiveCatagory, String> {
    @Query(value = "select o from ObjectiveCatagory o")
    List<ObjectiveCatagory> getApprisalTypeQuery(@Param("SearchString") String searchString);
}