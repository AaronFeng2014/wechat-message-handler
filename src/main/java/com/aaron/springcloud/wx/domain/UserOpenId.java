package com.aaron.springcloud.wx.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-28
 */
@Setter
@Getter
@ToString
public class UserOpenId
{
    @JSONField(name = "openid")
    private String openId;

    @JSONField(name = "lang")
    private String language = "zh_CN";


    public UserOpenId(String openId)
    {
        this.openId = openId;
    }
}
