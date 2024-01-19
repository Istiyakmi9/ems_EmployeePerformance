package com.bot.performance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AppraisalComment {
    @JsonProperty("Id")
    Long id;
    @JsonProperty("Name")
    String name;
    @JsonProperty("Comments")
    String comments;
    @JsonProperty("CommentedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date commentedOn;
}
