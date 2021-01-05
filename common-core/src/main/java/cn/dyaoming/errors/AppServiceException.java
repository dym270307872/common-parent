package cn.dyaoming.errors;


/**
 * <p>
 * 业务层异常类
 * </p>
 *
 * @author DYAOMING
 * @since 2020-01-29
 * @version 0.0.3
 */
public class AppServiceException extends BaseException {

    private static final long serialVersionUID = 109554980329675422L;



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppServiceException(String message) {
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
    public AppServiceException(String code, String message) {
        super(message);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param cause Throwable类型 异常
     */
    public AppServiceException(Throwable cause) {
        super(cause);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     * @param cause Throwable类型 异常
     */
    public AppServiceException(String message, Throwable cause) {
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
    public AppServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }



    public static AppServiceException create(String message) {
        return new AppServiceException(message);
    }



    public static AppServiceException create(String code, String message) {
        return new AppServiceException(code, message);
    }



    public static AppServiceException create(String code, String message, Throwable cause) {
        return new AppServiceException(code, message, cause);
    }
}
