package com.aaron.springcloud.wx.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-28
 */
@Setter
@Getter
@ToString
public class SubscribersOpenIdList
{
    private int total;

    private int count;

    @JSONField (name = "next_openid")
    private String nextOpenId;

    @JSONField (name = "data")
    private List<UserOpenId> openIdList;
}
