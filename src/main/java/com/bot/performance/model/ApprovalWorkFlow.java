package com.bot.performance.model;

import com.bot.performance.db.annotations.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "approval_work_flow")
public class ApprovalWorkFlow {
    @JsonProperty("ApprovalWorkFlowId")
    int approvalWorkFlowId;
    @JsonProperty("Title")
    String title;
    @JsonProperty("TitleDescription")
    String titleDescription;
    @JsonProperty("Status")
    int status;
    @JsonProperty("IsAutoExpiredEnabled")
    boolean isAutoExpiredEnabled;
    @JsonProperty("AutoExpireAfterDays")
    int autoExpireAfterDays;
    @JsonProperty("NoOfApprovalLevel")
    int noOfApprovalLevel;
    @JsonProperty("IsSilentListner")
    boolean isSilentListner;
    @JsonProperty("ListnerDetail")
    String listnerDetail;
}
