package com.aaron.springcloud.wx.message;

import com.aaron.springcloud.wx.constants.MessageTypeEnums;
import com.aaron.springcloud.wx.constants.MessageUrl;
import com.aaron.springcloud.wx.message.msgbody.MiniProgramCard;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 在客服消息中给用户发送小程序卡片
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/5
 */
@Setter
@Getter
@ToString (callSuper = true)
public class MiniProgramMessage extends CostumerMessage
{
    @JSONField (name = "miniprogrampage")
    private MiniProgramCard miniProgramPage;


    public MiniProgramMessage(MiniProgramCard miniProgram, String accessToken)
    {
        super(MessageTypeEnums.MINI_PROGRAM.getType(), MessageUrl.COSTUMER_MESSAGE_URL + accessToken);

        this.miniProgramPage = miniProgram;
    }
}
