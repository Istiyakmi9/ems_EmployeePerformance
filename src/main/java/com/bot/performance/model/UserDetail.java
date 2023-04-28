package com.bot.performance.model;

public class UserDetail {
    Long EmployeeId;
    Long UserId;
    int OrganizationId;
    int CompanyId;
    String FirstName;
    String LastName;
    String FullName;
    String ManagerName;
    String Mobile;
    String Email;
    int RoleId;
    String Password;
    Long ReportingManagerId;
    int AdminId;

    public Long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(Long employeeId) {
        EmployeeId = employeeId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public int getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(int organizationId) {
        OrganizationId = organizationId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Long getReportingManagerId() {
        return ReportingManagerId;
    }

    public void setReportingManagerId(Long reportingManagerId) {
        ReportingManagerId = reportingManagerId;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    public UserDetail(Long employeeId, Long userId, int organizationId, int companyId, String firstName, String lastName, String fullName, String managerName, String mobile, String email, int roleId, String password, Long reportingManagerId, int adminId) {
        EmployeeId = employeeId;
        UserId = userId;
        OrganizationId = organizationId;
        CompanyId = companyId;
        FirstName = firstName;
        LastName = lastName;
        FullName = fullName;
        ManagerName = managerName;
        Mobile = mobile;
        Email = email;
        RoleId = roleId;
        Password = password;
        ReportingManagerId = reportingManagerId;
        AdminId = adminId;
    }

    public UserDetail() {}

    @Override
    public String toString() {
        return "UserDetail{" +
                "EmployeeId=" + EmployeeId +
                ", UserId=" + UserId +
                ", OrganizationId=" + OrganizationId +
                ", CompanyId=" + CompanyId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", FullName='" + FullName + '\'' +
                ", ManagerName='" + ManagerName + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Email='" + Email + '\'' +
                ", RoleId=" + RoleId +
                ", Password='" + Password + '\'' +
                ", ReportingManagerId=" + ReportingManagerId +
                ", AdminId=" + AdminId +
                '}';
    }
}
