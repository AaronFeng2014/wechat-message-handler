package com.aaron.springcloud.wx.cache.impl.memory;

import com.aaron.springcloud.wx.cache.CacheItem;
import com.aaron.springcloud.wx.domain.QrCodeCacheItem;
import com.aaron.springcloud.wx.exception.ExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 二维码内存缓存
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018/11/8
 */
public class QrCodeMemoryCacheRepository  implements CacheItem<QrCodeCacheItem>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaMemoryCacheRepository.class);

    private static final int MAX_CACHE_SIZE = 1000;

    /**
     * 二维码缓存
     */
    private static final Map<Long, QrCodeCacheItem> QRCODE_CACHE = new LinkedHashMap<Long, QrCodeCacheItem>()
    {

        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, QrCodeCacheItem> eldest)
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
        long hashCode = key.hashCode();

        QrCodeCacheItem qrCodeCacheItem = QRCODE_CACHE.get(hashCode);

        if (qrCodeCacheItem == null)
        {
            return "";
        }

        try
        {
            String qrCodeUrl = qrCodeCacheItem.getQrCodeUrl();
            LOGGER.info("从缓存中加载到资源，QrCodeUrl：{}", qrCodeUrl);
            return qrCodeUrl;
        }
        catch (ExpiredException e)
        {
            //移除缓存
            LOGGER.info("资源已过期，从缓存中移除，key：{}", hashCode);

            QRCODE_CACHE.remove(hashCode);

            return "";
        }
    }


    @Override
    public void save(String key, QrCodeCacheItem cacheItem)
    {
        try
        {
            long hashCode = key.hashCode();
            LOGGER.info("已保存到缓存，QrCodeUrl：{}，key：{}", cacheItem.getQrCodeUrl(), hashCode);
            QRCODE_CACHE.put(hashCode, cacheItem);
        }
        catch (ExpiredException e)
        {
            e.printStackTrace();
        }
    }
}