package cn.dyaoming.sync;

import java.util.Collection;

import org.springframework.cache.Cache;

public interface SyncManager {

    /**
     * <p>获取锁配置</p>
     * @param name the Sync identifier (must not be {@code null})
     * @return the associated cache, or {@code null} if none found
     */
    Cache getSync(String name);

    /**
     * Return a collection of the cache names known by this manager.
     * @return the names of all caches known by the cache manager
     */
    Collection<String> getSyncNames();
}
