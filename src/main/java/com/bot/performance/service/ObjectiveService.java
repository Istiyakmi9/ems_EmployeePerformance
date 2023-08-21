package com.bot.performance.service;

import com.bot.performance.model.CurrentSession;
import com.bot.performance.model.DbParameters;
import com.bot.performance.model.ObjectiveDetail;
import com.bot.performance.repository.PerformanceObjectiveRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
