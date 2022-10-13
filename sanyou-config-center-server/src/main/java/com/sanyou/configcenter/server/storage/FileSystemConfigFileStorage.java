package com.sanyou.configcenter.server.storage;

import com.alibaba.fastjson.JSON;
import com.sanyou.configcenter.server.pojo.ConfigFile;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件系统
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/10/2 22:04
 */
@Slf4j
public class FileSystemConfigFileStorage implements ConfigFileStorage {

    /**
     * 文件的存储路径
     */
    private final String fileStorePath = System.getProperty("user.home") + File.separator + "sanyou"
            + File.separator + "config";

    /**
     * 缓存，减少与磁盘交互，查询操作直接从缓存中查找即可
     */
    private final ConfigFileStorage cache = new InMemoryConfigFileStorage();

    /**
     * 一个配置文件对应一个磁盘文件
     */
    private final Map<String, File> fileMap = new ConcurrentHashMap<>();

    /**
     * 刚启动时，从磁盘加载文件到内存中
     */
    @PostConstruct
    public void loadFiles() {

        log.info("配置文件存储路径为:{}", fileStorePath);

        File dir = new File(fileStorePath);

        dir.mkdirs();

        File[] files = dir.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(this::loadFile);
        }
    }

    private void loadFile(File file) {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));

            byte[] bytes = new byte[(int) file.length()];

            bis.read(bytes);

            ConfigFile configFile = JSON.parseObject(new String(bytes), ConfigFile.class);

            fileMap.put(configFile.getFileId(), file);

            cache.save(configFile);

        } catch (Exception e) {
            log.error("loadFile 异常:{}", e.getMessage(), e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("io close 异常:{}", e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void save(ConfigFile configFile) {

        String fileId = configFile.getFileId();

        //文件的名称就是文件的id
        File file = new File(fileStorePath + File.separator + fileId);

        writeToFile(file, configFile);

        fileMap.put(fileId, file);

        cache.save(configFile);
    }

    @Override
    public void update(ConfigFile configFile) {

        String fileId = configFile.getFileId();

        File file = getFile(fileId);

        writeToFile(file, configFile);

        cache.update(configFile);
    }

    @Override
    public void delete(String fileId) {
        File file = getFile(fileId);

        file.delete();

        cache.delete(fileId);
    }

    @Override
    public ConfigFile selectByFileId(String fileId) {
        return cache.selectByFileId(fileId);
    }

    @Override
    public List<ConfigFile> selectAll() {
        return cache.selectAll();
    }

    private void writeToFile(File file, ConfigFile configFile) {

        String json = JSON.toJSONString(configFile);

        BufferedOutputStream bos = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));

            bos.write(json.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            log.error("writeToFile 异常:{}", e.getMessage(), e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("io close 异常:{}", e.getMessage(), e);
                }
            }
        }
    }

    private File getFile(String fileId) {
        File file = fileMap.get(fileId);
        if (file == null) {
            throw new RuntimeException("文件id=" + fileId + "未找到对应的文件");
        }
        return file;
    }

}
