package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    @JsonProperty("EmployeeId")
    Long employeeId;
    @JsonProperty("UserId")
    Long userId;
    @JsonProperty("OrganizationId")
    int organizationId;
    @JsonProperty("CompanyId")
    int companyId;
    @JsonProperty("FirstName")
    String firstName;
    @JsonProperty("LastName")
    String lastName;
    @JsonProperty("FullName")
    String fullName;
    @JsonProperty("ManagerName")
    String managerName;
    @JsonProperty("Mobile")
    String mobile;
    @JsonProperty("Email")
    String email;
    @JsonProperty("RoleId")
    int roleId;
    @JsonProperty("Password")
    String password;
    @JsonProperty("ReportingManagerId")
    Long reportingManagerId;
    @JsonProperty("AdminId")
    int adminId;
    @JsonProperty("DesignationId")
    int designationId;
}
