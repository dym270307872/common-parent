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
public class AppRunTimeOutException extends BaseRunTimeException {

    private static final long serialVersionUID = 7899356287467829429L;
    /**
     * 默认错误码
     */
    private final static String ERROR_CODE = "4081";



    /**
     * <p>
     * 构造函数
     * </p>
     */
    public AppRunTimeOutException() {
        super("Your request has timed out");
        setCode(ERROR_CODE);
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
        setCode(ERROR_CODE);
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
        setCode(ERROR_CODE);
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
        setCode(ERROR_CODE);
    }



    @Override
    public String getCode() {
        return "B" + super.getCode();
    }



    public AppRunTimeOutException create(String message) {
        return new AppRunTimeOutException(message);
    }



    public AppRunTimeOutException create(String code, String message) {
        return new AppRunTimeOutException(code, message);
    }



    public AppRunTimeOutException create(String code, String message, Throwable cause) {
        return new AppRunTimeOutException(code, message, cause);
    }
}