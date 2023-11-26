package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data

public class AppraisalLevel {
    @JsonProperty("RoleId")
    int roleId;
    @JsonProperty("ObjectiveCatagoryId")
    int objectiveCatagoryId;
    @JsonProperty("IsDefaultChain")
    boolean isDefaultChain;
    @JsonProperty("Chain")
    List<ChainDetail> chain;
}
