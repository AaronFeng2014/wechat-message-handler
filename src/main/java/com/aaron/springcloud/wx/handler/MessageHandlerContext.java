package com.aaron.springcloud.wx.handler;

import com.alibaba.fastjson.JSON;
import java.io.InputStream;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 消息处理器链
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-05
 */
public class MessageHandlerContext
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerContext.class);

    /**
     * 消息处理链
     */
    private List<MessageHandlerAdapterContext> messageHandlerList = new ArrayList<>();


    /**
     * 微信事件回调消息处理入口
     *
     * @param params String：微信的回调参数
     * @param appId String：消息所属的appId
     */
    public void handleMessageChain(String params, String appId)
    {
        if (CollectionUtils.isEmpty(messageHandlerList))
        {
            LOGGER.warn("没有配置消息处理器，消息不会被处理，原始消息内容：{}", params);

            return;
        }

        //xml参数解析
        Map<String, String> formatParams = XmlUtils.parseToMap(params);

        // 发送者openid
        String senderOpenId = formatParams.get("FromUserName");

        LOGGER.info("接收到来自{}的消息，消息内容是：{}", senderOpenId, JSON.toJSONString(formatParams));

        AtomicBoolean hasHandle = new AtomicBoolean(false);
        messageHandlerList.stream().filter(messageHandler -> messageHandler.support(appId)).forEach(messageHandler -> {

            messageHandler.doHandle(formatParams);

            hasHandle.set(true);
        });

        //一个处理器都没有被匹配到，提示一个警告信息
        if (!hasHandle.get())
        {
            LOGGER.warn("未找到对应的消息处理器，消息未被消费，请检查是否为appId为{}的小程序或者服务号配置了消息处理器", appId);
        }
    }


    /**
     * 注册messageHandlerAdapterContext
     *
     * @param messageHandlerAdapterContexts MessageHandlerAdapterContext：消息处理上下文，可变参数列表
     *
     * @return 返回MessageHandlerContext，为了支持脸是调用
     */
    public MessageHandlerContext addMessageHandler(MessageHandlerAdapterContext... messageHandlerAdapterContexts)
    {
        Assert.notNull(messageHandlerAdapterContexts, "messageHandler不能为空");

        if (ArrayUtils.isEmpty(messageHandlerAdapterContexts))
        {
            throw new IllegalArgumentException("messageHandler不能为空");
        }

        Stream.of(messageHandlerAdapterContexts).forEach(messageHandlerAdapterContext -> {

            messageHandlerList.add(messageHandlerAdapterContext);

            LOGGER.info("已注册公众号或小程序事件处理器，appId：{}", messageHandlerAdapterContext.getAppId());

        });

        return this;
    }


    private static class XmlUtils
    {
        static Map<String, String> parseToMap(String xml)
        {
            return parseXml(xml);
        }


        /**
         * 解析XML为Document对象
         *
         * @param xml 被解析的XMl
         *
         * @return Document
         */
        private static Map<String, String> parseXml(String xml)
        {
            Document document;

            try
            {
                InputStream inputStream = IOUtils.toInputStream(xml, "UTF-8");

                SAXReader saxReader = new SAXReader();

                document = saxReader.read(inputStream);

            }
            catch (Exception e)
            {
                LOGGER.error("xml解析失败", e);
                return Collections.emptyMap();
            }

            if (document == null)
            {
                return Collections.emptyMap();
            }

            List<Element> elements = document.getRootElement().elements();

            return elements.stream().collect(Collectors.toMap(Element::getName, ele -> String.valueOf(ele.getData())));
        }
    }
}