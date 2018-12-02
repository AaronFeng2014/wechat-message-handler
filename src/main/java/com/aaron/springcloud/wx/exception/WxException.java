package com.aaron.springcloud.wx.exception;

/**
 * 微信请求数据处理异常
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/9
 */
public class WxException extends RuntimeException
{
    private int code;


    public WxException(int code, String message)
    {
        super(message);
        this.code = code;
    }
}
