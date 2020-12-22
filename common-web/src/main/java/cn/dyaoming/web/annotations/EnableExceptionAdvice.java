package cn.dyaoming.web.annotations;

import org.springframework.context.annotation.Import;

import cn.dyaoming.web.advices.BaseExceptionAdvice;

/**
 * <p>自定义统一异常处理器注解</p>
 * 
 * @author DYAOMING
 * @since 2020/12/22
 * @version 0.0.5
 */
@Import(BaseExceptionAdvice.class)
public @interface EnableExceptionAdvice {

}
