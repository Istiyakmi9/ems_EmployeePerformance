package com.bot.performance.service;

import com.bot.performance.model.EmployeeSalaryDetail;
import com.bot.performance.model.PromotionAndHike;
import com.bot.performance.repository.EmployeeSalaryDetailRepository;
import com.bot.performance.serviceinterface.IPromotionAndHikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PromotionAndHikeService implements IPromotionAndHikeService {
    @Autowired
    EmployeeSalaryDetailRepository employeeSalaryDetailRepository;
    public PromotionAndHike addPromotionAndHike(List<PromotionAndHike> promotionAndHikes) throws Exception {
        for (var promotionDetail: promotionAndHikes) {
            if (promotionDetail.getEmployeeId() == 0)
                throw new Exception("Invalid employee selected");

            var employeeData =employeeSalaryDetailRepository.findById(promotionDetail.getEmployeeId());
            if (employeeData == null)
                throw new Exception("Employee detail not found");
            var employeeSalaryDetail = employeeData.get();
            validateHikeDetail(promotionDetail, employeeSalaryDetail);
        }
        return  null;
    }

    private void validateHikeDetail(PromotionAndHike promotionAndHike, EmployeeSalaryDetail employeeSalaryDetail) throws Exception {
        BigDecimal proposedHikeAmount = (employeeSalaryDetail.getCTC().multiply(promotionAndHike.getProposedHikePercentage()))
                .divide(new BigDecimal(100));
        proposedHikeAmount = proposedHikeAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        if (!proposedHikeAmount.equals(promotionAndHike.getProposedHikeAmount()))
            throw new Exception("Proposed hike amount calculation mismatched");
    }
}
