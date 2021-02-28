package cn.dyaoming.errors;


/**
 * <p>
 * 服务层异常类
 * </p>
 *
 * @author DYAOMING
 * @since 2021/01/05
 * @version 0.0.6
 */
public class AppServerException extends BaseException {

    private static final long serialVersionUID = 109554980329675422L;



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppServerException(String message) {
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
    public AppServerException(String code, String message) {
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
    public AppServerException(Throwable cause) {
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
    public AppServerException(String message, Throwable cause) {
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
    public AppServerException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }

    
    @Override
    public String getCode() {
        return super.getCode() != null ? "C" + super.getCode() : null;
    }


    public static AppServerException create(String message) {
        return new AppServerException(message);
    }



    public static AppServerException create(String code, String message) {
        return new AppServerException(code, message);
    }



    public static AppServerException create(String code, String message, Throwable cause) {
        return new AppServerException(code, message, cause);
    }
}
