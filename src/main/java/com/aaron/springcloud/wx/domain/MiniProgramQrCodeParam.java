package com.aaron.springcloud.wx.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 小程序二维码生成参数
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
@Setter
@Getter
@ToString
public class MiniProgramQrCodeParam
{
    private String scene;

    private String page;

    private Integer width;

    @JSONField (name = "auto_color")
    private String autoColor;

    @JSONField (name = "is_hyaline")
    private boolean isHyaline = true;
}
