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
public abstract class AppRunTimeException extends RuntimeException {

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
    public AppRunTimeException(String message) {
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
    public AppRunTimeException(String code, String message) {
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
    public AppRunTimeException(String message, Throwable cause) {
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
    public AppRunTimeException(String code, String message, Throwable cause) {
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
    public AppRunTimeException(Throwable cause) {
        super(cause);
    }



    public abstract <T> T create(String message);



    public abstract <T> T create(String code, String message);



    public abstract <T> T create(String code, String message, Throwable cause);



    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }

}
