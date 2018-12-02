package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.Text;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 在客服消息中给用户发送文本消息
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
@Setter
@Getter
@ToString (callSuper = true)
public class TextMessage extends CostumerMessage
{

    private Text text;


    public TextMessage(Text text, String accessToken)
    {
        super(MessageTypeEnums.TEXT.getType(), MessageUrl.COSTUMER_MESSAGE_URL + accessToken);

        this.text = text;
    }
}
