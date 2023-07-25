package com.bot.performance.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PromotionAndHike {
    @Column(name = "EmployeeId")
    Long employeeId;
    @Column(name = "ProposedPromotion")
    int proposedPromotion;
    @Column(name = "ProposedHikePercentage")
    BigDecimal proposedHikePercentage;
    @Column(name = "ProposedHikeAmount")
    BigDecimal proposedHikeAmount;
    @Column(name = "NewCTC")
    BigDecimal newCTC;
}
