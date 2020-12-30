package cn.dyaoming.cache.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;

import org.springframework.context.annotation.Import;

import cn.dyaoming.cache.DefaultCacheConfig;

/**
 * 
 * <p>使用注解形式启动自定义缓存</p>
 * <p>1、新建缓存实现类，实现CacheInterface接口，使用cacheDao作为bean名称</p>
 * <p>2、使用config.cache.cacheNames 标记预设缓存配置，(示例：userInfo#500#true#0,publicInfo#50),标识预设两种缓存空间，第一种命名userInfo，缓存时长500s，需要加密，使用数据库下标0（对redis有效）；第二种命名publicInfo，缓存时长50s，不加密，使用默认数据库下标</p>
 * @author DYAOMING
 * @since 2020/10/28
 * @version 0.0.5
 */
@Target(ElementType.TYPE)
@Documented
@Inherited
@Import(value = { DefaultCacheConfig.class })
public @interface EnableCommonCache {

}
