package com.sanyou.configcenter.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * 微信公众号：三友的java日记
 *
 * @author sanyou
 * @date 2022/10/12 12:56
 */
@RefreshScope
@Service
public class UserService {

    @Value("${sanyou.username}")
    private String username;

    public String getUsername() {
        return username;
    }

}
