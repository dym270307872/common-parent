package cn.dyaoming.sync.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * <p>同步锁注解</p>
 * 
 * @author dyaoming
 * @since 2020/12/23
 * @version 0.0.5
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SyncLock {

    /**
     * <p>工作空间</p>
     * {@link #syncNames}
     * @return value
     */
    @AliasFor("syncNames")
    String[] value() default {};

    /**
     * <p>工作空间</p>
     * {@link #value}
     * @return syncNames
     */
    @AliasFor("value")
    String[] syncNames() default {};

    /**
     * <p>自定义同步锁识别钥匙</p>
     * @return 自定义锁键值
     */
    String key() default "";

    /**
     * <p>自定义同步锁钥匙生成序列</p>
     * @return 锁键值生成序列
     */
    String keyGenerator() default "";
    
}
