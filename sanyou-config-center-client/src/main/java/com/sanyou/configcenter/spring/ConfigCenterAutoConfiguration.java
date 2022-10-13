package com.sanyou.configcenter.spring;

import com.sanyou.configcenter.client.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/30 00:38
 */
@Configuration
public class ConfigCenterAutoConfiguration {

    @Bean
    public ConfigCenterProperties configCenterProperties() {
        return new ConfigCenterProperties();
    }

    @Bean
    public ConfigService configService(ConfigCenterProperties configCenterProperties) {
        return new ConfigService(configCenterProperties.getServerAddr());
    }

    @Bean
    public ConfigContextRefresher configContextRefresher() {
        return new ConfigContextRefresher();
    }

}
