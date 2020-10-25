package cn.dyaoming.web.advices;


import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dyaoming.models.BaseRestModel;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;


@SuppressWarnings("rawtypes")
public abstract class BaseResponseBodyAdvice implements ResponseBodyAdvice {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private static final Class<? extends Annotation> ANNOTATION_TYPE = RestController.class;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        if (body instanceof BaseRestModel) {
            return (BaseRestModel) body;
        } else if (body instanceof String) {
            HttpHeaders headers = response.getHeaders();
            headers.add("content-type", "application/json;charset=UTF-8");
            try {
                return new ObjectMapper().writeValueAsString(BaseRestModel.success(body));
            } catch (Exception e) {
                e.printStackTrace();
                // throw AppMessageException.()
            }
        }
        return BaseRestModel.success(body);
    }

}
