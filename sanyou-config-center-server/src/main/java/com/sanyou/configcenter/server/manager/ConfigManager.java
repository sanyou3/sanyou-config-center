package com.sanyou.configcenter.server.manager;

import com.sanyou.configcenter.server.pojo.ConfigFile;
import com.sanyou.configcenter.server.storage.ConfigFileStorage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * config manager
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/30 00:01
 */
@Component
public class ConfigManager {

    @Resource
    private ConfigFileStorage configFileStorage;

    public String save(String name, String extension, String content) {

        ConfigFile configFile = new ConfigFile();
        configFile.setFileId(UUID.randomUUID().toString());
        configFile.setName(name);
        configFile.setExtension(extension);
        configFile.setContent(content);
        configFile.setLastUpdateTimestamp(System.currentTimeMillis());

        configFileStorage.save(configFile);

        return configFile.getFileId();
    }

    public void update(String fileId, String name, String extension, String content) {
        ConfigFile configFile = new ConfigFile();
        configFile.setFileId(fileId);
        configFile.setName(name);
        configFile.setExtension(extension);
        configFile.setContent(content);
        configFile.setLastUpdateTimestamp(System.currentTimeMillis());

        configFileStorage.update(configFile);
    }

    public void delete(String fileId) {
        configFileStorage.delete(fileId);
    }

    public ConfigFile selectByFileId(String fileId) {
        return configFileStorage.selectByFileId(fileId);
    }

    public List<ConfigFile> selectAll() {
        return configFileStorage.selectAll();
    }

}
