package com.aaron.springcloud.wx.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 微信服务号或小程序配置信息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/7
 */
@Component
@ConfigurationProperties (prefix = "wechat.config")
@Setter
@Getter
@ToString
public class AppConfigurations
{
    private Set<AppConfig> appConfigList = new HashSet<>();


    public String getToken(String appId)
    {

        for (AppConfig appConfig : appConfigList)
        {
            if (!appConfig.getAppId().equals(appId))
            {
                continue;
            }

            return appConfig.getToken();
        }

        throw new RuntimeException("未找到" + appId + "对应的token");
    }
}
