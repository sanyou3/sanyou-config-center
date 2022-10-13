package com.sanyou.configcenter.server.storage;

import com.sanyou.configcenter.server.pojo.ConfigFile;

import java.util.List;

/**
 * 配置中心存储层
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/29 23:42
 */
public interface ConfigFileStorage {

    /**
     * 保存
     *
     * @param configFile
     */
    void save(ConfigFile configFile);

    /**
     * 修改
     *
     * @param configFile
     */
    void update(ConfigFile configFile);

    /**
     * 删除文件
     *
     * @param fileId
     */
    void delete(String fileId);

    /**
     * 通过文件id查找
     *
     * @param fileId
     * @return
     */
    ConfigFile selectByFileId(String fileId);

    /**
     * 查找所有
     *
     * @return
     */
    List<ConfigFile> selectAll();

}
