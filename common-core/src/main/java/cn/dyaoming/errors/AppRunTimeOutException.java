<<<<<<< HEAD
package cn.dyaoming.errors;


/**
 * <p>
 * 系统超时异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.5
 * @since 2020-09-11
 */
public class AppRunTimeOutException extends AppRunTimeException {


    private static final long serialVersionUID = 7899356287467829429L;

    /**
	 * <p>
	 * 构造函数
	 * </p>
	 *
	 */
	public AppRunTimeOutException() {
		super("Your request has timed out");
	}

    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppRunTimeOutException(String message) {
        super(message);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param code String类型 异常标识
     * @param message String类型 异常信息
     */
    public AppRunTimeOutException(String code, String message) {
        super(message);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     * @param cause 异常
     */
    public AppRunTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param code String类型 异常标识
     * @param message String类型 异常信息
     * @param cause Throwable类型 异常
     */
    public AppRunTimeOutException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param cause String类型 异常
     */
    public AppRunTimeOutException(Throwable cause) {
        super(cause);
    }

}
=======
package cn.dyaoming.errors;


/**
 * <p>
 * 系统超时异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.3
 * @since 2020-01-29
 */
public class AppRunTimeOutException extends BaseException {


    private static final long serialVersionUID = 7899356287467829429L;



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppRunTimeOutException(String message) {
        super(message);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param code String类型 异常标识
     * @param message String类型 异常信息
     */
    public AppRunTimeOutException(String code, String message) {
        super(message);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     * @param cause 异常
     */
    public AppRunTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param code String类型 异常标识
     * @param message String类型 异常信息
     * @param cause Throwable类型 异常
     */
    public AppRunTimeOutException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param cause String类型 异常
     */
    public AppRunTimeOutException(Throwable cause) {
        super(cause);
    }

}
>>>>>>> 4cc56f581bcec0821758f52ea881e0860d6e9d44
