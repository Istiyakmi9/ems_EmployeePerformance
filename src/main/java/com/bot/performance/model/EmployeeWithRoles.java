package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeWithRoles {
    @JsonProperty("DesignationId")
    int designationId;
    @JsonProperty("EmployeeUid")
    long employeeUid;
    @JsonProperty("Email")
    String email;
    @JsonProperty("Name")
    String name;
    @JsonProperty("IsRequired")
    boolean isRequired;
    @JsonProperty("Status")
    int status;
}
