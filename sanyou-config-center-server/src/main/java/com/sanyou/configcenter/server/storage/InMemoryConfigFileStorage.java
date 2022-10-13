package com.sanyou.configcenter.server.storage;

import com.sanyou.configcenter.server.pojo.ConfigFile;
import com.sanyou.configcenter.server.storage.ConfigFileStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于内存存储
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/29 23:50
 */
public class InMemoryConfigFileStorage implements ConfigFileStorage {

    private final Map<String, ConfigFile> configFileMap = new ConcurrentHashMap<>();

    @Override
    public void save(ConfigFile configFile) {
        configFileMap.put(configFile.getFileId(), configFile);
    }

    @Override
    public void update(ConfigFile configFile) {
        configFileMap.put(configFile.getFileId(), configFile);
    }

    @Override
    public void delete(String fileId) {
        configFileMap.remove(fileId);
    }

    @Override
    public ConfigFile selectByFileId(String fileId) {
        return configFileMap.get(fileId);
    }

    @Override
    public List<ConfigFile> selectAll() {
        return new ArrayList<>(configFileMap.values());
    }

}
