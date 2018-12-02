package com.aaron.springcloud.wx.domain;

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
public class ActionInfo
{

    @JSONField (name = "scene")
    private Scene scene;


    ActionInfo(String sceneStr)
    {
        this.scene = new Scene(sceneStr);
    }
}
