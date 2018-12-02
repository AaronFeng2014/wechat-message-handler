package com.aaron.springcloud.wx.handler;

import lombok.Setter;

import java.util.Map;
import java.util.function.Consumer;

/**
 * 微信事件回调，自定义逻辑处理，在类实例需要注册到{@link MessageHandlerAdapterContext 的 messageHandlerAdapters属性}中
 * <p>
 * 注册地点在{@link com.aaron.springcloud.wx.AbstractMessageCallBackController}的 afterPropertiesSet方法中
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-09
 */
public abstract class AbstractMessageHandlerAdapter implements Consumer<Map<String, String>>
{

    /**
     * 小程序或者服务号对应的appId
     */
    @Setter
    protected String appId;


    @Override
    public final void accept(Map<String, String> params)
    {
        try
        {
            this.messageHandle(params);
        }
        catch (Exception e)
        {
            this.exceptionCaught(params, e);
        }
    }


    /**
     * 逻辑处理的地方，在该逻辑中用户可以不用关心异常处理
     * <p>
     * 异常会被传递到exceptionCaught方法中，用户可以在exceptionCaught方法中去做异常后的逻辑
     *
     * @param params Map<String, String>：微信回调参数
     */
    protected abstract void messageHandle(Map<String, String> params);


    /**
     * 异常处理的地方，比如可以给用户发一个错误的提示信息之类什么的，默认处理异常的方式是直接抛出
     * <p>
     * 特殊逻辑需开发者自己实现
     *
     * @param params Map<String, String>：微信回调参数
     * @param e Exception：messageHandle方法抛出的异常
     */
    protected void exceptionCaught(Map<String, String> params, Exception e)
    {
        try
        {
            throw e;
        }
        catch (Exception ignore)
        {

        }
    }
}