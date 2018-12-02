package com.aaron.springcloud.wx.register;

import lombok.Getter;

/**
 * 微信事件回调枚举
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
@Getter
public enum WxCallBackTypeEnum
{

    /*
     * start 微信普通消息，即用户主动给公众号发消息的事件枚举
     * 在微信回调参数中是以MsgType标记的
     */
    /**
     * 发送文本消息
     */
    SEND_TEXT_MESSAGE("text", "发送文本消息"),

    /**
     * 发送图片消息
     */
    SEND_IMAGE_MESSAGE("image", "发送发送图片消息"),

    /**
     * 发送声音消息
     */
    SEND_VOICE_MESSAGE("voice", "发送声音消息"),

    /**
     * 发送视频消息
     */
    SEND_VIDEO_MESSAGE("video", "发送视频消息"),
    /**
     * 发短视频消息
     */
    SEND_SHORT_VIDEO_MESSAGE("shortvideo", "发送短视频消息"),

    /**
     * 发送地理位置消息
     */
    SEND_LOCATION_MESSAGE("location", "发送地理位置消息"),

    /**
     * 发送链接消息
     */
    SEND_LINK_MESSAGE("link", "发送链接消息"),

    /*
     * end 微信普通消息，即用户主动给公众号发消息的事件枚举
     * 在微信回调参数中是以MsgType标记的
     */

    /*
     * start 微信事件推送枚举
     * <p>
     * 在微信回调参数中是以Event标记的
     */
    /**
     * 关注公众号事件
     */
    SUBSCRIBE_EVENT("subscribe", "关注公众号事件"),

    /**
     * 取消关注公众号事件
     */
    UNSUBSCRIBE_EVENT("unsubscribe", "取消关注公众号事件"),

    SCAN_EVENT("SCAN", ""),

    /**
     * 上报地理位置事件
     */
    LOCATION_EVENT("LOCATION", "上报地理位置事件"),

    /**
     * 点击自定义菜单事件
     * 注意：点击子菜单无效
     */
    CLICK_MENU_EVENT("CLICK", "点击自定义菜单事件"),

    /**
     * 点击菜单上的链接跳转事件
     */
    VIEW_MENU_EVENT("VIEW", "点击菜单上的链接跳转事件");

    /*
     * end 微信事件推送枚举
     * <p>
     * 在微信回调参数中是以Event标记的
     */

    /**
     * 微信回调的事件或者消息类型
     */
    private String type;

    /**
     * 本地处理的方法名称
     */
    private String description;


    WxCallBackTypeEnum(String type, String description)
    {
        this.type = type;
        this.description = description;
    }
}