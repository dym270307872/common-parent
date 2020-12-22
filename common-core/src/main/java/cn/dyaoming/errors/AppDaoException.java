package cn.dyaoming.errors;


/**
 * <p>
 * 系统数据访问层异常
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.3
 * @since 2020-01-29
 */
public class AppDaoException extends BaseException {

    private static final long serialVersionUID = 1774990961999200277L;



    /**
     * <p>
     * 构造函数
     * </p>
     *
     * @param message String类型 异常信息
     */
    public AppDaoException(String message) {
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
    public AppDaoException(String code, String message) {
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
    public AppDaoException(String message, Throwable cause) {
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
    public AppDaoException(String code, String message, Throwable cause) {
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
    public AppDaoException(Throwable cause) {
        super(cause);
    }



    public AppDaoException create(String message) {
        return new AppDaoException(message);
    }



    public AppDaoException create(String code, String message) {
        return new AppDaoException(code, message);
    }



    public AppDaoException create(String code, String message, Throwable cause) {
        return new AppDaoException(code, message, cause);
    }
}
