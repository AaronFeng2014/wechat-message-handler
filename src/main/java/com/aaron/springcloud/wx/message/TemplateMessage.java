package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.message.msgbody.MiniProgram;
import com.aaron.springcloud.wx.message.msgbody.TemplateItem;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.TreeMap;

/**
 * 模板消息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/11
 */
@Setter
@Getter
@ToString (callSuper = true)
public class TemplateMessage
{
    /**
     * 消息接收者
     */
    private String touser;

    @JSONField (name = "template_id")
    private String templateId;

    private String url;

    /**
     * 模板消息需要跳转小程序的时候需要该参数
     */
    @JSONField (name = "miniprogram")
    private MiniProgram miniProgram;

    private TreeMap<String, TemplateItem> data;


    public TemplateMessage(String touser, String templateId, TreeMap<String, TemplateItem> data)
    {
        this.touser = touser;
        this.templateId = templateId;
        this.data = data;
    }
}