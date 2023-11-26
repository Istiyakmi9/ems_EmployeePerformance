package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "appraisal_review_finalizer_status")
public class AppraisalReviewFinalizerStatus {
    @Id
    @Column(name = "AppraisalFinalizer")
    @JsonProperty("AppraisalFinalizer")
    int appraisalFinalizer;
    @Column(name = "AppraisalReviewId")
    @JsonProperty("AppraisalReviewId")
    long appraisalReviewId;
    @Column(name = "ReviwerId")
    @JsonProperty("ReviwerId")
    long reviwerId;
    @Column(name = "Email")
    @JsonProperty("Email")
    String email;
    @Column(name = "Status")
    @JsonProperty("Status")
    int status;
    @Column(name = "ReactedOn")
    @JsonProperty("ReactedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date reactedOn;
    @Column(name = "FullName")
    @JsonProperty("FullName")
    String fullName;
    @Column(name = "IsActionRequired")
    @JsonProperty("IsActionRequired")
    boolean isActionRequired;
    @Column(name = "ApprovalLevel")
    @JsonProperty("ApprovalLevel")
    int approvalLevel;
}
