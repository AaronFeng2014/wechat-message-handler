package com.aaron.springcloud.wx.domain;

import com.aaron.springcloud.wx.exception.ExpiredException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.Getter;

/**
 * 临时媒体资源
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public class MediaCacheItem
{
    @Getter
    private String mediaId;

    private LocalDateTime createTime = LocalDateTime.now();


    public MediaCacheItem(String mediaId)
    {
        this.mediaId = mediaId;
    }


    /**
     * 临时素材是否过期
     *
     * @return 返回true表示已经过期了
     */
    private boolean isExpired()
    {
        long between = ChronoUnit.HOURS.between(createTime, LocalDateTime.now());

        //微信官方说是3天过期，这里取70个小时吧
        return between > 70;
    }


    /**
     * 自定义缓存实现时，通过该方式获取缓存值，主动检查是否过期，过期了抛出异常
     *
     * @return 媒体资源media_id
     */
    public String getMediaId() throws ExpiredException
    {
        if (isExpired())
        {
            throw new ExpiredException("缓存内容已过期，mediaId：" + mediaId);
        }

        return mediaId;
    }
}