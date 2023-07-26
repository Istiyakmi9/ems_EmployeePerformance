package com.bot.performance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
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
