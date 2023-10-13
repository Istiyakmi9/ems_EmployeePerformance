package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data

public class TeamMemberAndAppraisalFinalizer {
    @JsonProperty("FullName")
    String fullName;
    @JsonProperty("Team")
    String team;
    @JsonProperty("ReactedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date reactedOn;
    @JsonProperty("ProjectName")
    String projectName;
    @JsonProperty("ProjectDescription")
    String projectDescription;
    @JsonProperty("Status")
    int status;
    @JsonProperty("ManagerName")
    String managerName;
    @JsonProperty("ProjectManagerId")
    int projectManagerId;
    @JsonProperty("ProjectId")
    int projectId;
}
