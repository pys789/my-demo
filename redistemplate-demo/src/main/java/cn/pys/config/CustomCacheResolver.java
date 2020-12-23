package cn.pys.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 自定义CacheResolver实现动态选择CacheManager
 * 优先从堆内存读取缓存，堆内存缓存不存在时再从redis读取缓存，
 * redis缓存不存在时最后从mysql读取数据，
 * 并将读取到的数据依次写到redis和堆内存中
 */
@Slf4j
public class CustomCacheResolver implements CacheResolver, InitializingBean {

    @Nullable
    private List<CacheManager> cacheManagerList;

    CustomCacheResolver(List<CacheManager> cacheManagerList) {
        this.cacheManagerList = cacheManagerList;
    }

    public List<CacheManager> getCacheManagerList() {
        return cacheManagerList;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.cacheManagerList, "CacheManager is required");
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        for (CacheManager cacheManager : getCacheManagerList()) {
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache != null) {
                    Set<Cache> singleton = Collections.singleton(cache);
                    return singleton;
                }
            }
        }
        return null;
    }

    private Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }
}
