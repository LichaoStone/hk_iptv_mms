package com.haikan.iptv.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "iptv")
public class IptvProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private boolean openAopLog = true;

}
