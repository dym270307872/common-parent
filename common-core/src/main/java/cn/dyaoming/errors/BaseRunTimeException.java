package cn.dyaoming.errors;


/**
 * <p>
 * 系统业务层异常
 * </p>
 *
 * @author DYAOMING
 * @since 2020-9-11
 * @version 0.0.5
 */
public abstract class BaseRunTimeException extends RuntimeException {

    private static final long serialVersionUID = -5996860581412314248L;

    /**
     * 异常错误码
     */
    private String code;



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message 异常信息
     */
    public BaseRunTimeException(String message) {
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
    public BaseRunTimeException(String code, String message) {
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
    public BaseRunTimeException(String message, Throwable cause) {
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
    public BaseRunTimeException(String code, String message, Throwable cause) {
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
    public BaseRunTimeException(Throwable cause) {
        super(cause);
    }



    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }

}
