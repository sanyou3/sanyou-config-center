package com.sanyou.configcenter.server.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 配置文件实体类
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/9/29 23:43
 */
@Getter
@Setter
@Accessors(chain = true)
public class ConfigFile {

    /**
     * 文件的唯一id
     */
    private String fileId;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件后缀名
     */
    private String extension;

    /**
     * 文件的内容
     */
    private String content;

    /**
     * 上一次更新的时间戳
     */
    private Long lastUpdateTimestamp;

}
