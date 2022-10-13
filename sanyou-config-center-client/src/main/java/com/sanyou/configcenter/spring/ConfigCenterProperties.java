package com.sanyou.configcenter.spring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置文件
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/30 11:38
 */
@ConfigurationProperties("spring.cloud.sanyou.config")
@Getter
@Setter
public class ConfigCenterProperties {

    /**
     * 配置中心的地址
     */
    private String serverAddr;

    /**
     * 配置文件的id
     */
    private String fileId;

}
