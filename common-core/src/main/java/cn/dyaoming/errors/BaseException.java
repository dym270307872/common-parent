package cn.dyaoming.errors;


/**
 * <p>
 * 基础异常类
 * </p>
 * 
 * @author DYAOMING
 */
public class BaseException extends RuntimeException {

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
    public BaseException() {
        super();
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public BaseException(String message) {
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
    public BaseException(String code, String message) {
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
    public BaseException(String message, Throwable cause) {
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
    public BaseException(String code, String message, Throwable cause) {
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
    public BaseException(Throwable cause) {
        super(cause);
    }



    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }
}
