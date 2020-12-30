package cn.dyaoming.web.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import cn.dyaoming.web.advices.BaseExceptionAdvice;

/**
 * <p>自定义统一异常处理器注解</p>
 * 
 * @author DYAOMING
 * @since 2020/12/22
 * @version 0.0.5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BaseExceptionAdvice.class)
public @interface EnableExceptionAdvice {

}
