package cn.dyaoming.errors;


import java.io.PrintStream;
import java.io.PrintWriter;


/**
 *  <p>系统服务层异常类</p>
 *
 * @author DYAOMING
 * @since 2019-3-13
 * @version  V1.0
 */
public class AppServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;



	/**
	 * <p>构造函数</p>
	 *
	 * @param message 异常信息
	 */
	public AppServiceException(String message) {
		super(message);
	}



	/**
	 * <p>构造函数</p>
	 *
	 * @param cause 异常
	 */
	public AppServiceException(Throwable cause) {
		super(cause);
	}



	/**
	 * <p>构造函数</p>
	 *
	 * @param message 异常信息
	 * @param cause   异常
	 */
	public AppServiceException(String message, Throwable cause) {
		super(message, cause);
	}



	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}



	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
	}



	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
	}



	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
