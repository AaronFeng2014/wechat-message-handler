package com.aaron.springcloud.wx.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Setter
@Getter
public class QrCode
{
    private String appId;

    private String sceneParam;


    public QrCode(String appId, String sceneParam)
    {
        this.appId = appId;
        this.sceneParam = sceneParam;
    }
}
