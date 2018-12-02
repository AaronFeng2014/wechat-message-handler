package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 在客服消息中给用户发送图片消息
 * <p>
 * 发送图片消息要先把图片通过微信的素材接口上传到微信的服务器，得到一个media_id，最后发送的内容其实是media_id
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/7
 */
@Setter
@Getter
@ToString (callSuper = true)
public class ImageMessage extends CostumerMessage
{
    private Image image;


    public ImageMessage(Image image, String accessToken)
    {
        super(MessageTypeEnums.IMAGE.getType(), MessageUrl.COSTUMER_MESSAGE_URL + accessToken);

        this.image = image;
    }
}
