package com.aaron.springcloud.wx.domain;

import com.aaron.springcloud.wx.constants.QrCodeTypeEnum;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-07
 */
@Setter
@Getter
@ToString
public class QrCodeRequest
{

    @JSONField (serialize = false)
    private String appId;

    @JSONField (name = "action_name")
    private String actionName = QrCodeTypeEnum.QR_LIMIT_STR_SCENE.getType();

    @JSONField (name = "action_info")
    private ActionInfo actionInfo;


    public QrCodeRequest(String appId, String sceneStr)
    {
        this.appId = appId;
        this.actionInfo = new ActionInfo(sceneStr);
    }
}
