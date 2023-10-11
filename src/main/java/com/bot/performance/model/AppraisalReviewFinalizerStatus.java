package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "appraisal_review_finalizer_status")
public class AppraisalReviewFinalizerStatus {
    @Id
    @Column(name = "AppraisalFinalizer")
    int appraisalFinalizer;
    @Column(name = "AppraisalReviewId")
    long appraisalReviewId;
    @Column(name = "ReviwerId")
    long reviwerId;
    @Column(name = "Email")
    String email;
    @Column(name = "Status")
    int status;
    @Column(name = "ReactedOn")
    Date reactedOn;
    @Column(name = "FullName")
    String fullName;
    @Column(name = "IsActionRequired")
    boolean isActionRequired;
    @Column(name = "ApprovalLevel")
    int approvalLevel;
}
