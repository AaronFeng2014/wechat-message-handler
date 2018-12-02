package com.aaron.springcloud.wx.menu;

import lombok.Getter;

/**
 * 创建菜单类型枚举
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Getter
public enum ButtonTypeEnum
{
    CLICK("click", ""),

    VIEW("view", ""),

    SCANCODE_WAITMSG("scancode_waitmsg", "扫码带提示"),

    SCANCODE_PUSH("scancode_push", "扫码推事件"),

    PIC_SYSPHOTO("pic_sysphoto", "系统拍照发图"),

    PIC_PHOTO_OR_ALBUM("pic_photo_or_album", "拍照或者相册发图"),

    LOCATION_SELECT("location_select", "发送位置"),

    MEDIA_ID("media_id", "图片"),

    VIEW_LIMITED("view_limited", "图文消息");

    private String type;

    private String description;


    ButtonTypeEnum(String type, String description)
    {
        this.type = type;
        this.description = description;
    }
}
