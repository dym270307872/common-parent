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
 * @since 2019-04-03
 * @version V1.0
 */
public class MyCacheManager extends AbstractCacheManager {

	private String	name	= "default";
	private long	timeout	= 300L;

	private boolean	secret;



	public void setName(String name) {
		this.name = name;
	}



	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	private Collection<? extends Cache> caches = Collections.emptySet();



	public void setCaches(Collection<? extends Cache> caches) {
		this.caches = caches;
	}



	protected Collection<? extends Cache> loadCaches() {
		return this.caches;
	}



	// @Nullable
	@Override
	protected Cache getMissingCache(String name) {
		return new SystemCache(this.name, this.timeout, this.secret);
	}
}
