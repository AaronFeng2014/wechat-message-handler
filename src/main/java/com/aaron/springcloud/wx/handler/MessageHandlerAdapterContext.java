package com.aaron.springcloud.wx.handler;

import com.aaron.springcloud.wx.register.WxCallBackTypeEnum;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * MessageHandlerAdapter
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class MessageHandlerAdapterContext
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerAdapterContext.class);

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(4);

    private static final AbstractMessageHandlerAdapter DEFAULT_HANDLER_ADAPTER = new AbstractMessageHandlerAdapter()
    {
        @Override
        protected void messageHandle(Map<String, String> params)
        {
            //获取事件类型, 微信回调中事件类型和消息类型的区分,两者是不同的字段
            String type = params.getOrDefault("Event", params.getOrDefault("MsgType", ""));

            LOGGER.warn("未注册相应的事件处理器，事件类型：{}", type);
        }
    };

    private static final List<AbstractMessageHandlerAdapter> SINGLE_DEFAULT_HANDLER_ADAPTER = ImmutableList.of(DEFAULT_HANDLER_ADAPTER);

    /**
     * 消息处理适配器
     */
    private Map<String, List<AbstractMessageHandlerAdapter>> messageHandlerAdapters = new HashMap<>();

    @Getter
    private String appId;


    /**
     * 构造函数
     *
     * @param appId String：指明该处理器adapter处理的消息对应的appId
     */
    public MessageHandlerAdapterContext(String appId)
    {
        Assert.isTrue(StringUtils.isNotEmpty(appId), "appId参数不能为空");

        this.appId = appId;
    }


    /**
     * 消息是否支持被指定消息处理器处理，一般通过消息中的appId来标记
     *
     * @param appId String：appId，这里是微信发送的消息中携带的appId
     *
     * @return 当消息能够被该消息器处理的时候返回true
     */
    boolean support(String appId)
    {
        return this.appId.equals(appId);
    }


    void doHandle(Map<String, String> params)
    {

        //获取事件类型, 微信回调中事件类型和消息类型的区分,两者是不同的字段
        String type = params.getOrDefault("Event", params.getOrDefault("MsgType", ""));

        //查找事件注册的处理器adapter
        messageHandlerAdapters.getOrDefault(type, SINGLE_DEFAULT_HANDLER_ADAPTER).forEach(consumer -> {

            //线程池异步执行
            EXECUTOR_SERVICE.execute(() -> {

                try
                {
                    //设置appId进去
                    consumer.setAppId(this.appId);

                    consumer.accept(params);
                }
                catch (Exception e)
                {
                    LOGGER.error("未处理的异常", e);
                }
            });

        });

    }


    /**
     * 注册事件处理器，在接收到微信的回调后，只会关心已经注册过的事件，同一个事件可以注册多个处理器
     *
     * @param callBack MessageAdapterTypeEnum： 参考{@link com.aaron.springcloud.wx.register.WxCallBackTypeEnum}中定义的枚举值，该参数表示用户关心的微信回调事件
     * @param adapters AbstractMessageHandlerAdapter：对应事件的处理器
     */
    public MessageHandlerAdapterContext addMessageHandlerAdapter(WxCallBackTypeEnum callBack, AbstractMessageHandlerAdapter... adapters)
    {
        Assert.notNull(adapters, "consumer参数不能为空");

        List<AbstractMessageHandlerAdapter> consumerList = messageHandlerAdapters.get(callBack.getType());

        if (consumerList == null)
        {
            List<AbstractMessageHandlerAdapter> newList = new ArrayList<>();

            Collections.addAll(newList, adapters);

            messageHandlerAdapters.put(callBack.getType(), newList);
        }
        else
        {
            Collections.addAll(consumerList, adapters);
        }

        LOGGER.info("已注册公众号或者小程序回调事件，appId：{},事件类型：{}", this.appId, callBack.getType());

        return this;
    }
}