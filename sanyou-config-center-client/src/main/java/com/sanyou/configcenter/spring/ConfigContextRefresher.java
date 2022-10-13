package com.sanyou.configcenter.spring;

import com.sanyou.configcenter.client.ConfigService;
import com.sanyou.configcenter.client.listener.ConfigFileChangedListener;
import com.sanyou.configcenter.client.pojo.ConfigFile;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;

/**
 * 自动刷新配置
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/30 12:49
 */
public class ConfigContextRefresher implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    @Resource
    private ConfigCenterProperties configCenterProperties;

    @Resource
    private ConfigService configService;

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        registerListeners();
    }

    /**
     * 对配置文件注册对应的监听器
     */
    private void registerListeners() {
        configService.addListener(configCenterProperties.getFileId(), new ConfigFileChangedListener() {
            @Override
            public void onFileChanged(ConfigFile configFile) {
                //发布事件，然后spring就会自动从配置中心拉取数据，修改Bean的属性
                applicationContext.publishEvent(new RefreshEvent(this, null, "Refresh Sanyou config"));
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        // 创建一个ConfigService，传入配置中心服务端的地址
        ConfigService configService = new ConfigService("localhost:8888");

        // 从服务端获取配置文件的内容，文件的id是新增配置文件时候自动生成
        ConfigFile config = configService.getConfig("69af6110-31e4-4cb4-8c03-8687cf012b77");

        // 对某个配置文件进行监听
        configService.addListener("69af6110-31e4-4cb4-8c03-8687cf012b77", new ConfigFileChangedListener() {
            @Override
            public void onFileChanged(ConfigFile configFile) {
                System.out.printf("fileId=%s配置文件有变动，最新内容为:%s%n", configFile.getFileId(), configFile.getContent());
            }
        });
    }

}
