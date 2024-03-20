package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.bot.performance.db.annotations.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name = "objective_catagory")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ObjectiveCatagory {
    @Id
    @Column(name = "ObjectiveCatagoryId")
    @JsonProperty("ObjectiveCatagoryId")
    int objectiveCatagoryId;
    @Column(name = "ObjectiveCatagoryType")
    @JsonProperty("ObjectiveCatagoryType")
    public String objectiveCatagoryType;
    @Column(name = "TypeDescription")
    @JsonProperty("TypeDescription")
    public String typeDescription;
    @Column(name = "RolesId")
    @JsonProperty("RolesId")
    public String rolesId;
    @Column(name = "ObjectivesId")
    @JsonProperty("ObjectivesId")
    public String objectivesId;
    @Column(name = "IsHikeApproval")
    @JsonProperty("IsHikeApproval")
    boolean isHikeApproval;
    @Column(name = "CreatedBy")
    @JsonProperty("CreatedBy")
    public Long createdBy;
    @Column(name = "UpdatedBy")
    @JsonProperty("UpdatedBy")
    public Long updatedBy;
    @Column(name = "CreatedOn")
    @JsonProperty("CreatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    public Date createdOn;
    @Column(name = "UpdatedOn")
    @JsonProperty("UpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    public Date updatedOn;
    @Column(name = "Status")
    @JsonProperty("Status")
    public String status;
    @Column(name = "ApprovalWorkflowId")
    @JsonProperty("ApprovalWorkflowId")
    public int approvalWorkflowId;
    @Transient
    @JsonProperty("Total")
    int total;
    @Transient
    @JsonProperty("RowIndex")
    int rowIndex;
    @Transient
    @JsonProperty("ObjectiveIds")
    List<Integer> objectiveIds;
    @Transient
    @JsonProperty("RoleIds")
    List<Integer> roleIds;
    @Transient
    @JsonProperty("AppraisalCycleStartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date appraisalCycleStartDate;
    @Transient
    @JsonProperty("AppraisalCycleEndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date appraisalCycleEndDate;
    @Transient
    @JsonProperty("IsActiveCycle")
    boolean isActiveCycle;
    @Transient
    @JsonProperty("AppraisalDetailId")
    int appraisalDetailId;
    @Transient
    @JsonProperty("AppraisalName")
    String appraisalName;
    @Transient
    @JsonProperty("RoleId")
    int roleId;
}

