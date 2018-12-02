package com.aaron.springcloud.wx.cache.impl.memory;

import com.aaron.springcloud.wx.cache.CacheItem;
import com.aaron.springcloud.wx.domain.AccessTokenCacheItem;
import com.aaron.springcloud.wx.exception.ExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AccessToken 内存缓存实现
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-10
 */
public class AccessTokenCacheMemoryRepository implements CacheItem<AccessTokenCacheItem>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaMemoryCacheRepository.class);

    private static final int MAX_CACHE_SIZE = 1000;

    /**
     * 临时素材缓存
     */
    private static final Map<String, AccessTokenCacheItem> ACCESS_TOKEN_CACHE = new LinkedHashMap<String, AccessTokenCacheItem>()
    {

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, AccessTokenCacheItem> eldest)
        {
            boolean overFlow = this.size() > MAX_CACHE_SIZE;

            if (overFlow)
            {
                LOGGER.warn("缓存容器已满，删除存留时间最长的数据，key：{}}", eldest.getKey());
            }

            return overFlow;
        }
    };


    @Override
    public String get(String appId)
    {
        AccessTokenCacheItem cacheItem = ACCESS_TOKEN_CACHE.get(appId);

        if (cacheItem == null)
        {
            return null;
        }
        try
        {
            String mediaId = cacheItem.getAccessToken();

            LOGGER.info("从缓存中加载到资源，media_id：{}", mediaId);

            return mediaId;
        }
        catch (ExpiredException e)
        {
            LOGGER.info("资源已过期，从缓存中移除，key：{}", appId);

            ACCESS_TOKEN_CACHE.remove(appId);

            return null;
        }
    }


    @Override
    public void save(String appId, AccessTokenCacheItem cacheItem)
    {
        ACCESS_TOKEN_CACHE.put(appId, cacheItem);

        LOGGER.info("已保存到缓存，mediaId：{}，key：{}", cacheItem.getAccessToken(), appId);
    }
}