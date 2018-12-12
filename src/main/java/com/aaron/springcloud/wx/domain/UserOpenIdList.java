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
public class UserOpenIdList
{

    @JSONField (name = "user_list")
    private List<UserOpenId> userList;


    public UserOpenIdList(List<UserOpenId> userList)
    {
        this.userList = userList;
    }
}
