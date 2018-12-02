package com.aaron.springcloud.wx.domain;

import com.aaron.springcloud.wx.exception.ExpiredException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 二维码缓存项
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public class QrCodeCacheItem
{
    private String qrCodeUrl;

    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 过期时间
     */
    private long expiredSeconds;


    public QrCodeCacheItem(String qrCodeUrl)
    {
        /**
         *  -1 永不过期
         */
        this(qrCodeUrl, -1);
    }


    public QrCodeCacheItem(String qrCodeUrl, long expiredSeconds)
    {
        this.qrCodeUrl = qrCodeUrl;
        this.expiredSeconds = expiredSeconds;
    }


    /**
     * 二维码是否过期，此过期方式被动计算，而非主动计算，也就是说要用户来获取值得时候才会计算是否过期
     *
     * @return 返回true表示已经过期了
     */
    private boolean isExpired()
    {
        if (expiredSeconds == -1)
        {
            return false;
        }

        long between = ChronoUnit.SECONDS.between(createTime, LocalDateTime.now());

        //微信官方说是2592000秒最大时间
        return between > expiredSeconds;
    }


    /**
     * 自定义缓存实现时，通过该方式获取缓存值，主动检查是否过期，过期了抛出异常
     *
     * @return 二维码可以直接访问的地址
     */
    public String getQrCodeUrl() throws ExpiredException
    {
        if (isExpired())
        {
            throw new ExpiredException("缓存内容已过期，qrCodeUrl：" + qrCodeUrl);
        }

        return qrCodeUrl;
    }
}