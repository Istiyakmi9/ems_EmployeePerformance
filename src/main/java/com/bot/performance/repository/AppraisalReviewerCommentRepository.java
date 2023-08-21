package com.bot.performance.repository;

import com.bot.performance.db.service.DbManager;
import com.bot.performance.model.AppraisalReviewerComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppraisalReviewerCommentRepository {
}
