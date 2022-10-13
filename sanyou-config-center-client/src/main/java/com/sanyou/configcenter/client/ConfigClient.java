package com.sanyou.configcenter.client;

import com.sanyou.configcenter.client.pojo.ConfigFile;
import org.springframework.web.client.RestTemplate;

/**
 * 配置中心client，与配置中心server端进行交互
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/30 00:16
 */
public class ConfigClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String serverAddr;

    public ConfigClient(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public ConfigFile getConfig(String fileId) {
        return restTemplate.getForObject("http://" + serverAddr + "/v1/config/" + fileId, ConfigFile.class);
    }

}
