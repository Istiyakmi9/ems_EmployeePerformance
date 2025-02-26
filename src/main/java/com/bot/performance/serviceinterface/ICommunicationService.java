package com.bot.performance.serviceinterface;

import com.bot.performance.model.ApiResponse;
import com.bot.performance.model.TextFileFolderDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "fileConverterService", url = "${file.saver.service.url}")
public interface ICommunicationService {
    @PostMapping(value = "File/SaveAsTextFile", consumes = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse SaveAsTextFile(@RequestBody TextFileFolderDetail textFileFolderDetail,
                               @RequestHeader Map<String, String> headers);

}
