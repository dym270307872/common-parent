package cn.dyaoming.errors;


import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * <p>系统数据访问层异常</p>
 *
 * @author DYAOMING
 * @version 0.0.1
 * @since 2019-3-13
 */
public class AppDaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;



	/**
	 * <p>构造函数</p>
	 *
	 * @param message 异常信息
	 */
	public AppDaoException(String message) {
		super(message);
	}



	/**
	 * <p>构造函数</p>
	 *
	 * @param message 异常信息
	 * @param cause   异常
	 */
	public AppDaoException(String message, Throwable cause) {
		super(message, cause);
	}



	/**
	 * <p>构造函数</p>
	 *
	 * @param cause 异常
	 */
	public AppDaoException(Throwable cause) {
		super(cause);
	}



	@Override
	public String getMessage() {
		return super.getMessage();
	}



	public Throwable getRootCause() {
		return super.getCause();
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

}
