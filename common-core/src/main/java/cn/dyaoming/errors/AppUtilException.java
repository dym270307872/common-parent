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
public class AppUtilException extends AppServerException {

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
     * @param code String类型 异常标识
     * @param message String类型 异常信息
     */
    public AppUtilException(String code, String message) {
        super(message);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message 异常信息
     * @param cause 异常
     */
    public AppUtilException(String message, Throwable cause) {
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
    public AppUtilException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
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



    public static AppUtilException create(String message) {
        return new AppUtilException(message);
    }



    public static AppUtilException create(String code, String message) {
        return new AppUtilException(code, message);
    }

    public static AppUtilException create(String message, Throwable cause) {
        return new AppUtilException(message, cause);
    }

    public static AppUtilException create(String code, String message, Throwable cause) {
        return new AppUtilException(code, message, cause);
    }
}
