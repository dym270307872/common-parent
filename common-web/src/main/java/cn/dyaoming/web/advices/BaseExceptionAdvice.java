package cn.dyaoming.web.advices;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dyaoming.errors.BaseException;
import cn.dyaoming.errors.BaseRunTimeException;
import cn.dyaoming.models.BaseRestModel;

/**
 * <p>
 * 异常统一拦截过滤器
 * </p>
 * 
 * @author DYAOMING
 * @since 2020-12-22
 * @version 0.0.5
 */
@SuppressWarnings("rawtypes")
@ControllerAdvice
public class BaseExceptionAdvice {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * <p>
	 * exception异常统一拦截器
	 * </p>
	 * 
	 * @param exception 异常对象
	 * @return rest统一对象
	 */
	@ExceptionHandler(value = Exception.class)
	@Order(500000)
	@ResponseBody
	public BaseRestModel handleException(Exception exception) {
		log.error("系统发生未察觉异常，异常内容：" + exception.getMessage(), exception);
		return BaseRestModel.failed();
	}

	/**
	 * <p>
	 * 自定义baseException异常统一拦截器
	 * </p>
	 * 
	 * @param baseException 异常对象
	 * @return rest统一对象
	 */
	@ExceptionHandler(value = BaseException.class)
	@Order(100000)
	@ResponseBody
	public BaseRestModel handleAppBaseException(BaseException baseException) {
		log.error("系统发生异常，通过控制层抛向前端。", baseException);
		return BaseRestModel.failed(baseException.getCode(), baseException.getMessage());
	}

	/**
	 * <p>
	 * 自定义运行时异常统一拦截器
	 * </p>
	 * 
	 * @param runTimeException 异常对象
	 * @return rest统一对象
	 */
	@ExceptionHandler(value = BaseRunTimeException.class)
	@Order(80000)
	@ResponseBody
	public BaseRestModel handleAppRunTimeException(BaseRunTimeException runTimeException) {
		log.debug("系统发生运行时异常，异常原因：" + runTimeException.getMessage(), runTimeException);
		return BaseRestModel.failed(runTimeException.getCode(), runTimeException.getMessage());
	}

	/**
	 * <p>
	 * exception异常统一拦截器
	 * </p>
	 * 
	 * @param exception 异常对象
	 * @return rest统一对象
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@Order(10000)
	public String handleNotSupportedException(HttpRequestMethodNotSupportedException exception,
			HttpServletRequest httpRequest) {
		log.debug("系统发生异常，异常内容：请求路径：" + httpRequest.getRequestURI(), exception);
        //设置状态码
        httpRequest.setAttribute("javax.servlet.error.status_code", HttpStatus.NOT_FOUND.value());
        return "forward:/error";
	}
}
