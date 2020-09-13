package cn.dyaoming.errors;


/**
 * <p>
 * 应用基础异常类
 * </p>
 * 
 * @author DYAOMING
 * @since 2020-9-11
 * @version 0.0.5
 */
public abstract class AppBaseException extends Exception {

    private static final long serialVersionUID = -20021811335698269L;

    /**
     * 异常错误码
     */
    private String code;



    /**
     * <p>
     * 构造函数
     * </p>
     */
    public AppBaseException() {
        super();
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppBaseException(String message) {
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
    public AppBaseException(String code, String message) {
        super(message);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     * @param cause Throwable类型 异常
     */
    public AppBaseException(String message, Throwable cause) {
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
    public AppBaseException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param cause Throwable类型 异常
     */
    public AppBaseException(Throwable cause) {
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
