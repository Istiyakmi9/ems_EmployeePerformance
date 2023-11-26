package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChainDetail {
    @JsonProperty("RoleId")
    int roleId;
    @JsonProperty("IsActive")
    Boolean isActive;
}
