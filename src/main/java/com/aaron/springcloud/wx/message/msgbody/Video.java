package com.aaron.springcloud.wx.message.msgbody;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Setter
@Getter
@ToString
public class Video
{

    private String title;

    @JSONField (name = "thumb_media_id")
    private String thumbMediaId;

    @JSONField (name = "media_id")
    private String mediaId;

    private String description;
}
