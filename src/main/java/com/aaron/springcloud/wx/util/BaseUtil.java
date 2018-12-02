package com.aaron.springcloud.wx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/9
 */
class BaseUtil
{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUtil.class);

    static final RestTemplate REST_TEMPLATE;

    static final HttpHeaders DEFAULT_HEADER;


    static
    {
        DEFAULT_HEADER = new HttpHeaders();
        //设置默认header
        DEFAULT_HEADER.setContentType(MediaType.APPLICATION_JSON);

        //设置 MessageConvert

        REST_TEMPLATE = new RestTemplate(ImmutableList.of(new FormHttpMessageConverter(), new FastJsonHttpMessageConverter()));
    }


    static JSONObject extractResponse(ResponseEntity<String> responseEntity)
    {

        if (!HttpStatus.OK.equals(responseEntity.getStatusCode()))
        {
            return null;
        }

        String entityBody = responseEntity.getBody();

        LOGGER.info("微信返回的结果是：{}", entityBody);

        return JSON.parseObject(entityBody);
    }


    static boolean isSuccess(JSONObject jsonObject)
    {

        if (jsonObject == null)
        {
            throw new RuntimeException("请求失败，未知异常");
        }

        //微信处理成功标志
        boolean success = !jsonObject.containsKey("errcode") || jsonObject.getLong("errcode") == 0L;

        if (!success)
        {
            LOGGER.error("请求微信资源错误：{}", jsonObject.getString("errmsg"));

            return false;
        }

        return true;
    }
}