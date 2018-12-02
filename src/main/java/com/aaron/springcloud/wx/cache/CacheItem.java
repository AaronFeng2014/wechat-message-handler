package com.aaron.springcloud.wx.cache;

/**
 * 缓存接口申明
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-11-10
 */
public interface CacheItem<T>
{
    /**
     * 获取缓存中的对象
     *
     * @param key String：缓存的key
     * @return key对应的value
     */
    String get(String key);


    /**
     * 缓存保存接口
     *
     * @param key String：缓存的key
     * @param cacheItem T：缓存实体
     */
    void save(String key, T cacheItem);
}
