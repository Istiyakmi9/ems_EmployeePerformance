package com.bot.performance.controller;

import com.bot.performance.model.ApiResponse;
import com.bot.performance.service.ObjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/eps/objective/")
public class ObjectiveController extends BaseController {
    @Autowired
    ObjectiveService objectiveService;

    @RequestMapping(value = "get/{catagoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getObjectiveByCatagory(@PathVariable int catagoryId) throws Exception {
        var result = this.objectiveService.getObjectiveByCatagoryId(catagoryId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
