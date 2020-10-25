package cn.dyaoming.web.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cn.dyaoming.web.advices.BaseExceptionAdvice;

@Configuration
@Import(BaseExceptionAdvice.class)
public @interface EnableExceptionAdvice {

}
