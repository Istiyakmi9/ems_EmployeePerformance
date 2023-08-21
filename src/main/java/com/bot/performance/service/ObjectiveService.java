package com.bot.performance.service;

import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.ObjectiveDetail;
import com.bot.performance.repository.PerformanceObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveService {
    @Autowired
    PerformanceObjectiveRepository performanceObjectiveRepository;
    @Autowired
    CurrentSession userDetail;

    public List<ObjectiveDetail> getObjectiveByCatagoryId(int catagoryId) throws Exception {
        if (catagoryId <= 0)
            throw new Exception("Invalid catagory id used");

        return performanceObjectiveRepository.getObjectiveByCatagoryIdRepository(catagoryId, userDetail.getUserDetail().getCompanyId());
    }
}
