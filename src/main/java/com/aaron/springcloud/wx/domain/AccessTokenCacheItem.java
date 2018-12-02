package com.aaron.springcloud.wx.domain;

import com.aaron.springcloud.wx.exception.ExpiredException;
import java.time.LocalDateTime;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-10
 */
public class AccessTokenCacheItem
{
    private String accessToken;

    private long expiredIn;

    /**
     * 创建时间，单位秒
     */
    private long createTime = LocalDateTime.now().getSecond();


    public AccessTokenCacheItem(String accessToken, long expiredIn)
    {
        this.accessToken = accessToken;
        this.expiredIn = expiredIn;
    }


    /**
     * 临时素材是否过期
     *
     * @return 返回true表示已经过期了
     */
    private boolean isExpired()
    {
        long between = LocalDateTime.now().getSecond() - createTime;

        //微信AccessToken过期时间判断，给10分钟的缓冲期
        return between >= expiredIn - 60 * 10;
    }


    public String getAccessToken()
    {
        if (isExpired())
        {
            throw new ExpiredException("AccessToken已过期，请重新获取");
        }
        return accessToken;
    }
}
