package com.sanyou.configcenter.spring;

import com.sanyou.configcenter.client.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配，用来适配SpringCloud配置中心
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/30 00:38
 */
@Configuration
public class ConfigCenterBootstrapConfiguration {

    @Bean
    public ConfigCenterPropertySourceLocator configCenterPropertySourceLocator() {
        return new ConfigCenterPropertySourceLocator();
    }

    @Bean
    public ConfigCenterProperties configCenterProperties() {
        return new ConfigCenterProperties();
    }

    @Bean
    public ConfigService configService(ConfigCenterProperties configCenterProperties) {
        return new ConfigService(configCenterProperties.getServerAddr());
    }

}
