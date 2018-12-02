package com.aaron.springcloud.wx.cache.impl.memory;

import com.aaron.springcloud.wx.cache.CacheItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * String类型的缓存
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-10
 */
public class StringValueCacheMemoryRepository implements CacheItem<String>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaMemoryCacheRepository.class);

    private static final int MAX_CACHE_SIZE = 1000;

    /**
     * String类型的value缓存
     */
    private static final Map<String, String> STRING_CACHE = new LinkedHashMap<String, String>()
    {

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest)
        {
            boolean overFlow = this.size() > MAX_CACHE_SIZE;

            if (overFlow)
            {
                LOGGER.warn("缓存容器已满，删除存留时间最长的数据，key：{}", eldest.getKey());
            }

            return overFlow;
        }
    };


    @Override
    public String get(String key)
    {
        return STRING_CACHE.get(key);
    }


    @Override
    public void save(String key, String cacheItem)
    {
        STRING_CACHE.put(key, cacheItem);
    }
}