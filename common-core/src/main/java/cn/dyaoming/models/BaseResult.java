package cn.dyaoming.models;


import java.io.Serializable;


/**
 * <p>
 * 基本返回结果集
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-17
 * @version 0.0.3
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean flag;
    private String code;
    private String message;



    /**
     * 默认构造函数
     */
    public BaseResult() {
        setFlag(true);
        setCode("0000");
        setMessage("业务执行成功");
    }



    /**
     * 基础构造函数
     * 
     * @param flag boolean类型 结果标志
     * @param code String类型 结果编码
     */
    public BaseResult(boolean flag, String code) {

        setFlag(flag);
        setCode(code);
        /* TODO message 未转换 */
        setMessage(code);
    }



    /**
     * 带结果说明的构造函数
     * 
     * @param flag boolean类型 结果标志
     * @param code String类型 结果编码
     * @param message String类型 结果说明
     */
    public BaseResult(boolean flag, String code, String message) {

        setFlag(flag);
        setCode(code);
        setMessage(message);
    }



    /**
     * <p>
     * 成功
     * </p>
     * 
     * @return BaseResult
     */
    public static BaseResult success() {
        return new BaseResult();
    }



    /**
     * <p>
     * 失败
     * </p>
     * 
     * @param code String类型 失败标识
     * @param message String类型 失败说明
     * @return BaseResult
     */
    public static BaseResult fail(String code, String message) {
        return new BaseResult(false, code, message);
    }



    public boolean isFlag() {
        return flag;
    }



    public void setFlag(boolean flag) {
        this.flag = flag;
    }



    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }



    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }

}
