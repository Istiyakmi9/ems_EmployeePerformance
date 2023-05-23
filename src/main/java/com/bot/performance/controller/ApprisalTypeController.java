package com.bot.performance.controller;

import com.bot.performance.model.ApiResponse;
import com.bot.performance.model.FilterModel;
import com.bot.performance.service.ApprisalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/eps/apprisalcatagory/")
public class ApprisalTypeController extends BaseController {
    @Autowired
    ApprisalTypeService apprisalTypeService;

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ResponseEntity<?> getApprisalTypeFilter(FilterModel filter) {
        var result = this.apprisalTypeService.getApprisalTypeByFilter(filter);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
