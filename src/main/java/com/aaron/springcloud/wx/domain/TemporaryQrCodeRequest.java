package com.aaron.springcloud.wx.domain;

import com.aaron.springcloud.wx.constants.QrCodeTypeEnum;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

/**
 * 临时二维码
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-07
 */
@Setter
@Getter
@ToString
public class TemporaryQrCodeRequest extends QrCodeRequest
{

    /**
     * 临时二维码过期时间，微信提供的可以自行设置的值，不设置默认30s
     */
    @JSONField (name = "expire_seconds")
    private int expireSeconds;


    public TemporaryQrCodeRequest(String appId, String sceneStr)
    {
        //指定资源的过期时间
        this(appId, 2592000, sceneStr);
    }


    public TemporaryQrCodeRequest(String appId, int expireSeconds, String sceneStr)
    {
        super(appId, sceneStr);

        super.setActionName(QrCodeTypeEnum.QR_STR_SCENE.getType());

        Assert.isTrue(expireSeconds <= 2592000, "expireSeconds最大值为2592000");

        this.expireSeconds = expireSeconds;
    }
}