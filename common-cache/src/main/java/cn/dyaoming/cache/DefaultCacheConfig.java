package cn.dyaoming.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@ComponentScan("cn.dyaoming.utils")
public class DefaultCacheConfig extends CachingConfigurerSupport{

    
    @Value("${config.cache.cacheNames:null}")
    private String cacheNames;
    
    private final static String SEPARATOR = ",";
    
    @Bean
    @Override
    public CacheManager cacheManager() {
        DefaultCacheManager cacheManager = new DefaultCacheManager();

        if (cacheNames != null) {
            List<Cache> caches = new ArrayList<>();
            for(String cacheName : cacheNames.split(SEPARATOR)) {
                caches.add(new SystemCache(cacheName));
            }
            cacheManager.setCaches(caches);
        }
        return cacheManager;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new cn.dyaoming.cache.CacheKeyGenerator();
    }

}
