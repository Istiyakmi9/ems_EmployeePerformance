package com.bot.performance.model;

import com.bot.performance.db.annotations.Column;
import com.bot.performance.db.annotations.Id;
import com.bot.performance.db.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appraisal_reviewer_comments")
public class AppraisalReviewerComment {
    @Column(name = "AppraisalReviewerCommentsId")
    @Id
    long appraisalReviewerCommentsId;
    @Column(name = "AppraisalReviewId")
    long appraisalReviewId;
    @Column(name = "ReviewerId")
    long reviewerId;
    @Column(name = "Comments")
    String comments;
    @Column(name = "ReactedOn")
    Date reactedOn;
    @Column(name = "Rating")
    BigDecimal rating;
    @Column(name = "ReviewStatus")
    int reviewStatus;
}
