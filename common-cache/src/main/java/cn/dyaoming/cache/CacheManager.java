package cn.dyaoming.cache;


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
 * @since 2019-04-09
 * @version 0.0.1
 */
public class CacheManager extends AbstractCacheManager {

    private String name = "default";
    private long timeout = 300L;

    private boolean secret;

    private String database;

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

        if ("true".equalsIgnoreCase(secret)) {
            this.secret = true;
        } else {
            this.secret = false;
        }
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



    // @Nullable
    @Override
    protected Cache getMissingCache(String name) {

        String thisName = name;
        long thisTimeout = this.timeout;
        boolean thisSecret = this.secret;
        String thisDatabase = this.database;
        if (name.indexOf("#") > 0) {

            String[] arg = name.split(SEPARATOR);
            int thisLength = arg.length;
            
            thisName = arg[0];
            if (thisLength > 1) {
                thisTimeout = Long.valueOf(arg[1]);
            }
            if (thisLength > 2) {
                if ("true".equalsIgnoreCase(arg[2])) {
                    thisSecret = true;
                } else {
                    thisSecret = false;
                }
            }
            if (thisLength > 3) {
                thisDatabase = arg[3];
            }
        }

        return new SystemCache(thisName, thisTimeout, thisSecret, thisDatabase);

    }
}
