package cn.dyaoming.errors;


/**
 * <p>
 * 系统消息异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.3
 * @since 2020-01-29
 */
public class AppMessageException extends AppRunTimeException {

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
     * @param code String类型 异常标识
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
     * @param message String类型 异常信息
     * @param cause 异常
     */
    public AppMessageException(String message, Throwable cause) {
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

    
    @Override
    public AppMessageException create(String message) {
        return new AppMessageException(message);
    }

    @Override
    public AppMessageException create(String code, String message) {
        return new AppMessageException(code, message);
    }

    @Override
    public AppMessageException create(String code, String message, Throwable cause) {
        return new AppMessageException(code, message, cause);
    }
}
