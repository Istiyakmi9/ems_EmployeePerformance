package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ApprovalChainDetail {
    @JsonProperty("ApprovalChainDetailId")
    int approvalChainDetailId;
    @JsonProperty("ApprovalWorkFlowId")
    int approvalWorkFlowId;
    @JsonProperty("AssignieId")
    long assignieId;
    @JsonProperty("AssignieeEmail")
    String assignieeEmail;
    @JsonProperty("IsRequired")
    boolean isRequired;
    @JsonProperty("LastUpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date lastUpdatedOn;
    @JsonProperty("ApprovalStatus")
    int approvalStatus;
    @JsonProperty("AutoExpireAfterDays")
    int autoExpireAfterDays;
    @JsonProperty("NoOfApprovalLevel")
    int noOfApprovalLevel;
    @JsonProperty("IsAutoExpiredEnabled")
    boolean isAutoExpiredEnabled;

}
