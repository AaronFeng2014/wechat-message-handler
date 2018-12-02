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
public class Scene
{

    @JSONField (name = "scene_str")
    private String sceneStr;


    Scene(String sceneStr)
    {
        this.sceneStr = sceneStr;
    }
}
