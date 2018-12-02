package com.aaron.springcloud.wx.message.msgbody;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/7
 */
@Setter
@Getter
@ToString
public class Text
{
    private String content;


    public Text(String content)
    {
        this.content = content;
    }
}
