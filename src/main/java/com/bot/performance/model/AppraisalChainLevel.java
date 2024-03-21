package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@Table(name = "appraisal_chain_level")
public class AppraisalChainLevel {
    @Id
    @JsonProperty("AppraisalChainLevelId")
    @Column(name="AppraisalChainLevelId")
    int appraisalChainLevelId;
    @JsonProperty("RoleId")
    @Column(name = "RoleId")
    int roleId;
    @JsonProperty("ObjectiveCatagoryId")
    @Column(name = "ObjectiveCatagoryId")
    int objectiveCatagoryId;
    @JsonProperty("ApprovalRoleId")
    @Column(name = "ApprovalRoleId")
    int approvalRoleId;
    @JsonProperty("IsActive")
    @Column(name = "IsActive")
    Boolean isActive;
    @JsonProperty("IsOptional")
    @Column(name = "IsOptional")
    Boolean isOptional;
}
