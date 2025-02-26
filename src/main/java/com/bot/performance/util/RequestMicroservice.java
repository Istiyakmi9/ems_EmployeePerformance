package com.bot.performance.util;

import com.bot.performance.model.DbConfigModal;
import com.bot.performance.model.Files;
import com.bot.performance.model.TextFileFolderDetail;
import com.bot.performance.serviceinterface.ICommunicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RequestMicroservice {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    ICommunicationService iCommunicationService;

    public Files saveTextFile(MicroserviceRequest microserviceRequest, TextFileFolderDetail textFileFolderDetail) throws Exception {
        Map<String, String> headers = setJsonTypeHeader(microserviceRequest);
        try {
            // Call the Feign client
            var status =iCommunicationService.SaveAsTextFile(textFileFolderDetail, headers);
            return mapper.convertValue(status.getResponseBody(), Files.class);
        } catch (Exception ex) {
            throw  new Exception(ex.getMessage(), ex);
        }
    }

    @Async
    private Map<String, String> setJsonTypeHeader(MicroserviceRequest microserviceRequest) throws Exception {
        Map<String, String> headers = new HashMap<>();
        //headers.setBearerAuth(microserviceRequest.getToken());

        DbConfigModal dbConfigModal = discretConnectionString(microserviceRequest.connectionString);
        headers.put("database", mapper.writeValueAsString(dbConfigModal));
        headers.put("companyCode", microserviceRequest.getCompanyCode());

        return headers;
    }

    private DbConfigModal discretConnectionString(String cs) {
        DbConfigModal dbConfigModal = new DbConfigModal();
        String[] partedText = cs.split(";");
        if (partedText.length > 1) {
            for (String text : partedText) {
                String[] mainText = text.split("=");
                if (mainText[0].equalsIgnoreCase("OrganizationCode")) {
                    dbConfigModal.setOrganizationCode(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("Code")) {
                    dbConfigModal.setCode(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("Schema")) {
                    dbConfigModal.setSchema(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("DatabaseName")) {
                    dbConfigModal.setDatabaseName(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("Server")) {
                    dbConfigModal.setServer(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("Port")) {
                    dbConfigModal.setPort(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("Database")) {
                    dbConfigModal.setDatabase(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("user id")) {
                    dbConfigModal.setUserId(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("Password")) {
                    dbConfigModal.setPassword(mainText[1]);
                } else if (mainText[0].equalsIgnoreCase("connection timeout")) {
                    dbConfigModal.setConnectionTimeout(mainText[1] != null ? Integer.parseInt(mainText[1]) : 0);
                } else if (mainText[0].equalsIgnoreCase("connection lifetime")) {
                    dbConfigModal.setConnectionLifetime(mainText[1] != null ? Integer.parseInt(mainText[1]) : 0);
                } else if (mainText[0].equalsIgnoreCase("min pool size")) {
                    dbConfigModal.setMinPoolSize(mainText[1] != null ? Integer.parseInt(mainText[1]) : 0);
                } else if (mainText[0].equalsIgnoreCase("max pool size")) {
                    dbConfigModal.setMaxPoolSize(mainText[1] != null ? Integer.parseInt(mainText[1]) : 0);
                } else if (mainText[0].equalsIgnoreCase("Pooling")) {
                    dbConfigModal.setPooling(mainText[1] != null && Boolean.parseBoolean(mainText[1]));
                }
            }
        }

        return dbConfigModal;
    }
}