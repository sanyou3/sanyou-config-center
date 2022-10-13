package com.sanyou.configcenter.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/10/3 00:00
 */
@ConfigurationProperties("config")
@Component
@Getter
@Setter
public class ConfigServerProperties {

    /**
     * 存储类型 ，memory:基于内存存储 file:文件系统存储
     */
    private String storeType = "file";

}
