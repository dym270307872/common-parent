package cn.dyaoming.errors;


/**
 * <p>
 * 系统消息异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.6
 * @since 2020-01-29
 */
public class AppMessageException extends BaseRunTimeException {

    private static final long serialVersionUID = 4077571693836525234L;


    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppMessageException(String message) {
        super(message);
    }


    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param code    String类型 异常标识
     * @param message String类型 异常信息
     */
    public AppMessageException(String code, String message) {
        super(message);
        setCode(code);
    }

    
    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param code    String类型 异常标识
     * @param message String类型 异常信息
     */
    public AppMessageException(BaseException exception) {
        this(exception.getCode(),exception.getMessage(),exception);
    }
    

    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     * @param cause   异常
     */
    public AppMessageException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param code    String类型 异常标识
     * @param message String类型 异常信息
     * @param cause   Throwable类型 异常
     */
    public AppMessageException(String code, String message, Throwable cause) {
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
    public AppMessageException(Throwable cause) {
        super(cause);
    }


    public static AppMessageException create(String message) {
        return new AppMessageException(message);
    }

    public static AppMessageException create(String code, String message) {
        return new AppMessageException(code, message);
    }

    public static AppMessageException create(String code, String message, Throwable cause) {
        return new AppMessageException(code, message, cause);
    }
    
    public static AppMessageException createFrom(BaseException exception) {
        return new AppMessageException(exception);
    }
}
