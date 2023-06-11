package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
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
    @Column(name = "IsSelfAppraisal")
    @JsonProperty("IsSelfAppraisal")
    boolean isSelfAppraisal;
    @Column(name = "AppraisalCycleFromDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("AppraisalCycleFromDate")
    public Date appraisalCycleFromDate;
    @Column(name = "AppraisalCycleToDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("AppraisalCycleToDate")
    public Date appraisalCycleToDate;
    @Column(name = "SelfAppraisalFromDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelfAppraisalFromDate")
    public Date selfAppraisalFromDate;
    @Column(name = "SelfAppraisalToDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelfAppraisalToDate")
    public Date selfAppraisalToDate;
    @Column(name = "IsMultiRaterFeedback")
    @JsonProperty("IsMultiRaterFeedback")
    boolean isMultiRaterFeedback;
    @Column(name = "SelectionPeriodFromDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelectionPeriodFromDate")
    public Date selectionPeriodFromDate;
    @Column(name = "SelectionPeriodToDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("SelectionPeriodToDate")
    public Date selectionPeriodToDate;
    @Column(name = "FeedbackFromDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("FeedbackFromDate")
    public Date feedbackFromDate;
    @Column(name = "FeedbackToDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("FeedbackToDate")
    public Date feedbackToDate;
    @Column(name = "IsDefaultRater")
    @JsonProperty("IsDefaultRater")
    boolean isDefaultRater;
    @Column(name = "IsAllowSelfAppraisal")
    @JsonProperty("IsAllowSelfAppraisal")
    boolean isAllowSelfAppraisal;
    @Column(name = "IsHikeApproval")
    @JsonProperty("IsHikeApproval")
    boolean isHikeApproval;
    @Column(name = "ReviewFromDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("ReviewFromDate")
    public Date reviewFromDate;
    @Column(name = "ReviewToDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("ReviewToDate")
    public Date reviewToDate;
    @Column(name = "NormalizationFromDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("NormalizationFromDate")
    public Date normalizationFromDate;
    @Column(name = "NormalizationToDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("NormalizationToDate")
    public Date normalizationToDate;
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
    @Transient
    @JsonProperty("Total")
    int total;
    @Transient
    @JsonProperty("Index")
    int index;
    @Transient
    @JsonProperty("ObjectiveIds")
    List<Integer> objectiveIds;
    @Transient
    @JsonProperty("RoleIds")
    List<Integer> roleIds;
}

