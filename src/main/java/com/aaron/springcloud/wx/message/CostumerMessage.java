package com.aaron.springcloud.wx.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客服消息父类
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
@Getter
@ToString
public class CostumerMessage
{
    @JSONField (deserialize = false)
    private String url;

    /**
     * 消息接收者
     */
    @Setter
    private String touser;

    /**
     * 消息类型
     */
    @JSONField (name = "msgtype")
    private String msgType;


    public CostumerMessage(String msgType, String url)
    {
        this.msgType = msgType;
        this.url = url;
    }
}