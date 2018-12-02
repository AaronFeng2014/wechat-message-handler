package com.aaron.springcloud.wx.constants;

import lombok.Getter;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Getter
public enum MediaResourceTypeEnum
{
    /**
     * 图片素材
     */
    IMAGE("image"),

    /**
     * 声音素材
     */
    VOICE("voice"),

    /**
     * 视屏素材
     */
    VIDEO("video"),

    /**
     * 缩略图素材
     */
    THUMB("thumb");

    private String type;


    MediaResourceTypeEnum(String type)
    {
        this.type = type;
    }
}
