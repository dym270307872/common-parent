package cn.dyaoming.errors;


/**
 * <p>
 * 系统资源繁忙异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.4
 * @since 2020-01-29
 */
public class AppBusyException extends BaseException {

    private static final long serialVersionUID = -1522975184308076926L;

    
    /**
     * <p>
     * 构造函数
     * </p>
     *
     */
    public AppBusyException() {
        super("to busy");
    }


    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppBusyException(String message) {
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
    public AppBusyException(String code, String message) {
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
    public AppBusyException(String message, Throwable cause) {
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
    public AppBusyException(String code, String message, Throwable cause) {
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
    public AppBusyException(Throwable cause) {
        super(cause);
    }

}
