package com.sanyou.configcenter.client.listener;

import com.sanyou.configcenter.client.pojo.ConfigFile;

/**
 * 文件内容变化监听器，当文件内容有变动的话，就会回调
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/30 09:56
 */
public interface ConfigFileChangedListener {

    /**
     * 文件修改之后会回调
     *
     * @param configFile
     */
    void onFileChanged(ConfigFile configFile);

}
