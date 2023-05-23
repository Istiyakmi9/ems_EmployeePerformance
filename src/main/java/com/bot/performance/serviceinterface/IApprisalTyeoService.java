package com.bot.performance.serviceinterface;

import com.bot.performance.model.FilterModel;
import com.bot.performance.model.ObjectiveCatagory;

import java.util.List;

public interface IApprisalTyeoService {
    List<ObjectiveCatagory> getApprisalTypeByFilter(FilterModel filter);
}
