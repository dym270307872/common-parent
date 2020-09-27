package cn.dyaoming.advices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dyaoming.errors.AppBaseException;
import cn.dyaoming.errors.AppRunTimeException;
import cn.dyaoming.models.BaseRestModel;


@SuppressWarnings("rawtypes")
public abstract class BaseExceptionAdvice {

    protected Logger log = LoggerFactory.getLogger(this.getClass());



    @ExceptionHandler(value = Exception.class)
    @Order(500000)
    @ResponseBody
    public BaseRestModel handleException(Exception exception) {
        log.error("系统发生未察觉异常，异常内容：" + exception.getMessage(), exception);
        return BaseRestModel.failed();
    }



    @ExceptionHandler(value = AppBaseException.class)
    @Order(100000)
    @ResponseBody
    public BaseRestModel handleAppBaseException(AppBaseException baseException) {
        log.error("系统发生异常，通过控制层抛向前端。", baseException);
        return BaseRestModel.failed(baseException.getCode(), baseException.getMessage());
    }



    @ExceptionHandler(value = AppRunTimeException.class)
    @Order(80000)
    @ResponseBody
    public BaseRestModel handleAppRunTimeException(AppRunTimeException runTimeException) {
        log.debug("系统发生运行时异常，异常原因：" + runTimeException.getMessage(), runTimeException);
        return BaseRestModel.failed(runTimeException.getCode(), runTimeException.getMessage());
    }
}
