package cn.dyaoming.sync;



import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;
import java.util.Collections;


/**
 * <p>
 * 缓存控制器
 * </p>
 * 
 * @author DYAOMING
 * @since 2020-04-25
 * @version 0.0.4
 */
public class DefaultSyncManager extends AbstractCacheManager {

    private String name = "default";
    private long timeout = 300L;

    private boolean secret;

    private String database;

    private final static String TRUE = "true";
    /**
     * 分隔符
     */
    private final static String SEPARATOR = "#";



    /**
     * <p>
     * 设置缓存名
     * </p>
     * 
     * @param name 缓存名称
     */
    public void setName(String name) {
        this.name = name;
    }



    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }



    /**
     * <p>
     * 加密标识设置
     * </p>
     * 
     * @param secret String类型 加密标识
     */
    public void setSecret(String secret) {
    	this.secret = TRUE.equalsIgnoreCase(secret);
    }



    public void setDatabase(String database) {
        this.database = database;
    }

    private Collection<? extends Cache> caches = Collections.emptySet();



    public void setCaches(Collection<? extends Cache> caches) {
        this.caches = caches;
    }



    @Override
    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }

}
