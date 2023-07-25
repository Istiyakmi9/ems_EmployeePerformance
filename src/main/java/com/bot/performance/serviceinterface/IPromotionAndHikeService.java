package com.bot.performance.serviceinterface;

import com.bot.performance.model.PromotionAndHike;

import java.util.List;

public interface IPromotionAndHikeService {
    PromotionAndHike addPromotionAndHike(List<PromotionAndHike> promotionAndHike) throws Exception;
}
