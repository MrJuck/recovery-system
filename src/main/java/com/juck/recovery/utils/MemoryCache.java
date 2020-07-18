package com.juck.recovery.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MemoryCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryCache.class);
    private static final LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.MINUTES)
            .recordStats()
            .build(new CacheLoader<String, Object>() {
                @Override
                public Integer load(String key) {
                    return -1;
                }
            });
    private MemoryCache() {
    }

    public static Object get(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return null;
    }

    public static void set(String key, Object obj) {
        cache.put(key, obj);
    }
}
