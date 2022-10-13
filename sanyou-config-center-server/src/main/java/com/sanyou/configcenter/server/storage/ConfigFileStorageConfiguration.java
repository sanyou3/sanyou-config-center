package com.sanyou.configcenter.server.storage;

import com.sanyou.configcenter.server.config.ConfigServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 存储配置
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/10/2 23:58
 */
@Configuration
public class ConfigFileStorageConfiguration {

    @Resource
    private ConfigServerProperties configServerProperties;

    @Bean
    @ConditionalOnMissingBean
    public ConfigFileStorage configFileStorage() {
        String storeType = configServerProperties.getStoreType().toLowerCase();
        if ("file".equals(storeType)) {
            return new FileSystemConfigFileStorage();
        }

        if ("memory".equals(storeType)) {
            return new InMemoryConfigFileStorage();
        }

        throw new RuntimeException("storeType=" + storeType + "无对应的ConfigFileStorage实现");
    }

}
