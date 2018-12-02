package com.aaron.springcloud.wx.exception;

/**
 * 数据过期异常
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-09
 */
public class ExpiredException extends RuntimeException
{
    public ExpiredException(String message)
    {
        super(message);
    }
}
