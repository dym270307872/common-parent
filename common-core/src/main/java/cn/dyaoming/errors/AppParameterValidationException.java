package cn.dyaoming.errors;


/**
 * <p>
 * 请求参数验证异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.3
 * @since 2020-01-29
 */
public class AppParameterValidationException extends BaseRunTimeException {

    private static final long serialVersionUID = 4077571693836525234L;



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppParameterValidationException(String message) {
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
    public AppParameterValidationException(String code, String message) {
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
    public AppParameterValidationException(String message, Throwable cause) {
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
    public AppParameterValidationException(String code, String message, Throwable cause) {
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
    public AppParameterValidationException(Throwable cause) {
        super(cause);
    }



    @Override
    public String getCode() {
        return super.getCode() != null ? "A" + super.getCode() : null;
    }



    public static AppParameterValidationException create(String message) {
        return new AppParameterValidationException(message);
    }



    public static AppParameterValidationException create(String code, String message) {
        return new AppParameterValidationException(code, message);
    }



    public static AppParameterValidationException create(String code, String message, Throwable cause) {
        return new AppParameterValidationException(code, message, cause);
    }
}
