package cn.dyaoming.errors;

/**
 * <p>
 * 通用工具运行异常类。
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.1
 * @since 2019.3.13
 */
public class AppUtilException extends AppBaseException {

	private static final long serialVersionUID = 5726765918962578254L;

	/**
	 * <p>
	 * 构造函数
	 * </p>
	 *
	 * @param message 异常信息
	 */
	public AppUtilException(String message) {
		super(message);
	}

	/**
	 * <p>
	 * 构造函数
	 * </p>
	 *
	 * @param cause 异常
	 */
	public AppUtilException(Throwable cause) {
		super(cause);
	}

	/**
	 * <p>
	 * 构造函数
	 * </p>
	 *
	 * @param message 异常信息
	 * @param cause   异常
	 */
	public AppUtilException(String message, Throwable cause) {
		super(message, cause);
	}

}
