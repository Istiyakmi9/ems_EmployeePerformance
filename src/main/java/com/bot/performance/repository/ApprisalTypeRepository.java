package com.bot.performance.repository;

import com.bot.performance.model.EmployeePerformance;
import com.bot.performance.model.ObjectiveCatagory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprisalTypeRepository extends JpaRepository<ObjectiveCatagory, Integer> {
    @Query(value = "call sp_objective_catagory_filter(:_SearchString, :_SortBy, :_PageIndex, :_PageSize)", nativeQuery = true)
    List<ObjectiveCatagory> getApprisalTypeQuery(@Param("_SearchString") String searchString,
                                             @Param("_SortBy") String sortBy,
                                             @Param("_PageIndex") int pageIndex,
                                             @Param("_PageSize") int pageSize);

    @Query(nativeQuery = true, value = "select o.* from objective_catagory o order by o.ObjectiveCatagoryId desc limit 1")
    ObjectiveCatagory getLastObjectiveCategory();
}