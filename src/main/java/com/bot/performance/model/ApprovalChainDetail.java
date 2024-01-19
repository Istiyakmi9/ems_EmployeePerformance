package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApprovalChainDetail {
    @JsonProperty("RoleName")
    String roleName;
    @JsonProperty("DesignationId")
    int designationId;
    @JsonProperty("CompanyId")
    int companyId;
    @JsonProperty("ProjectId")
    int projectId;
    @JsonProperty("EmployeeId")
    long employeeId;
    @JsonProperty("FullName")
    String fullName;
    @JsonProperty("Email")
    String email;
    @JsonProperty("IsOptional")
    boolean isOptional;
}
