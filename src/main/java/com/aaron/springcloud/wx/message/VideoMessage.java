package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.Video;
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
@ToString (callSuper = true)
public class VideoMessage extends CostumerMessage
{
    private Video video;


    public VideoMessage(Video video, String accessToken)
    {
        super(MessageTypeEnums.VIDEO.getType(), MessageUrl.COSTUMER_MESSAGE_URL + accessToken);
        this.video = video;
    }
}
