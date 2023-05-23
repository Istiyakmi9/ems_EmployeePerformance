package com.bot.performance.service;

import com.bot.performance.model.FilterModel;
import com.bot.performance.model.ObjectiveCatagory;
import com.bot.performance.repository.ApprisalTypeRepository;
import com.bot.performance.serviceinterface.IApprisalTyeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprisalTypeService implements IApprisalTyeoService {

    @Autowired
    ApprisalTypeRepository apprisalTypeRepository;

    @Override
    public List<ObjectiveCatagory> getApprisalTypeByFilter(FilterModel filter) {
        List<ObjectiveCatagory> objectiveCatagorys = this.apprisalTypeRepository.getApprisalTypeQuery(filter.getSearchString());
        return objectiveCatagorys;
    }
}
