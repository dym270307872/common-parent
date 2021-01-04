package cn.dyaoming.errors;


/**
 * <p>
 * 系统权限异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.5
 * @since 2020-09-14
 */
public class AppAccessException extends BaseRunTimeException {

    private static final long serialVersionUID = -1522975184308076926L;

    /**
     * 默认错误码
     */
    private final static String ERROR_CODE = "4031";



    /**
     * <p>
     * 构造函数
     * </p>
     */
    public AppAccessException() {
        super("Unauthorized access");
        setCode(ERROR_CODE);
    }



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppAccessException(String message) {
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
    public AppAccessException(String code, String message) {
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
    public AppAccessException(String message, Throwable cause) {
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
    public AppAccessException(String code, String message, Throwable cause) {
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
    public AppAccessException(Throwable cause) {
        super(cause);
        setCode(ERROR_CODE);
    }



    @Override
    public String getCode() {
        return super.getCode() != null ? "B" + super.getCode() : null;
    }



    public static AppAccessException create(String message) {
        return new AppAccessException(message);
    }



    public static AppAccessException create(String code, String message) {
        return new AppAccessException(code, message);
    }



    public static AppAccessException create(String code, String message, Throwable cause) {
        return new AppAccessException(code, message, cause);
    }

}