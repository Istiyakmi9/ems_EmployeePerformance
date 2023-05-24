package com.bot.performance.repository;

import com.bot.performance.model.ObjectiveCatagory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprisalTypeRepository extends JpaRepository<ObjectiveCatagory, Integer> {
    @Query(nativeQuery = true, value = "select o.* from objective_catagory o order by o.ObjectiveCatagoryId desc limit 1")
    ObjectiveCatagory getLastObjectiveCategory();
}