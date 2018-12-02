package com.aaron.springcloud.wx.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 小程序或者服务号配置信息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/6
 */
@Setter
@Getter
@ToString
public class AppConfig
{
    private String appId;

    private String token;

    private String appSecret;

    private String encodingAesKey;


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AppConfig appConfig = (AppConfig)o;

        return appId != null ? appId.equals(appConfig.appId) : appConfig.appId == null;
    }


    @Override
    public int hashCode()
    {
        return appId != null ? appId.hashCode() : 0;
    }
}
