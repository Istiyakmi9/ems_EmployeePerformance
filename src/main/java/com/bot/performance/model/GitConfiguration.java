package com.bot.performance.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("performance-service")
@Data
public class GitConfiguration {
    String name;
    String folder;
}
