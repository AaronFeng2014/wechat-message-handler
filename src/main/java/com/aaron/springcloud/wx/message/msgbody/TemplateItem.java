package com.aaron.springcloud.wx.message.msgbody;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/11
 */
@Getter
@Setter
@ToString
public class TemplateItem
{
    @JSONField (name = "value")
    private String content;

    @JSONField (name = "color")
    private String contentColor;


    public TemplateItem(String content, String contentColor)
    {
        this.content = content;
        this.contentColor = contentColor;
    }
}