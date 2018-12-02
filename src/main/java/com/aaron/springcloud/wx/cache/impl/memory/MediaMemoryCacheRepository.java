package com.aaron.springcloud.wx.cache.impl.memory;

import com.aaron.springcloud.wx.cache.CacheItem;
import com.aaron.springcloud.wx.domain.MediaCacheItem;
import com.aaron.springcloud.wx.exception.ExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 媒体资源内存缓存
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public final class MediaMemoryCacheRepository implements CacheItem<MediaCacheItem>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MediaMemoryCacheRepository.class);

    private static final int MAX_CACHE_SIZE = 1000;

    /**
     * 临时素材缓存
     */
    private static final Map<String, MediaCacheItem> MEDIA_CACHE = new LinkedHashMap<String, MediaCacheItem>()
    {

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, MediaCacheItem> eldest)
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
    public String get(String mediaIndex)
    {
        MediaCacheItem cacheMedia = MEDIA_CACHE.get(mediaIndex);

        if (cacheMedia == null)
        {
            return null;
        }
        try
        {
            String mediaId = cacheMedia.getMediaId();

            LOGGER.info("从缓存中加载到资源，media_id：{}", mediaId);

            return mediaId;
        }
        catch (ExpiredException e)
        {
            LOGGER.info("资源已过期，从缓存中移除，key：{}", mediaIndex);

            MEDIA_CACHE.remove(mediaIndex);

            return null;
        }
    }


    @Override
    public void save(String key, MediaCacheItem cacheItem)
    {
        MEDIA_CACHE.put(key, cacheItem);

        LOGGER.info("已保存到缓存，mediaId：{}，key：{}", cacheItem.getMediaId(), key);
    }

}