package com.sanyou.configcenter.server.controller;

import com.sanyou.configcenter.server.pojo.ConfigFile;
import com.sanyou.configcenter.server.pojo.request.CreateConfigRequest;
import com.sanyou.configcenter.server.pojo.request.UpdateConfigRequest;
import com.sanyou.configcenter.server.manager.ConfigManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 配置中心
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/29 23:39
 */
@RestController
@RequestMapping("/v1/config")
public class ConfigController {

    @Resource
    private ConfigManager configManager;

    @PostMapping
    public String create(@RequestBody CreateConfigRequest createConfigRequest) {
        return configManager.save(createConfigRequest.getName(), createConfigRequest.getExtension(), createConfigRequest.getContent());
    }

    @PutMapping
    public void update(@RequestBody UpdateConfigRequest updateConfigRequest) {
        configManager.update(updateConfigRequest.getFileId(), updateConfigRequest.getName(), updateConfigRequest.getExtension(), updateConfigRequest.getContent());
    }

    @DeleteMapping("/{fileId}")
    public void delete(@PathVariable("fileId") String fileId) {
        configManager.delete(fileId);
    }

    @GetMapping("/{fileId}")
    public ConfigFile select(@PathVariable("fileId") String fileId) {
        return configManager.selectByFileId(fileId);
    }

    @GetMapping("/all")
    public List<ConfigFile> selectAll() {
        return configManager.selectAll();
    }

}
