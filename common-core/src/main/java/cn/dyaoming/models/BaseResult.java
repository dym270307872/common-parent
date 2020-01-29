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
     * @param cFlag boolean类型 结果标志
     * @param cCode String类型 结果编码
     */
    public BaseResult(boolean cFlag, String cCode) {

        setFlag(cFlag);
        setCode(cCode);
        /* TODO message 未转换 */
        setMessage(cCode);
    }



    /**
     * 带结果说明的构造函数
     * 
     * @param cFlag boolean类型 结果标志
     * @param cCode String类型 结果编码
     * @param cMsg String类型 结果说明
     */
    public BaseResult(boolean cFlag, String cCode, String cMsg) {

        setFlag(cFlag);
        setCode(cCode);
        setMessage(cMsg);
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
     * @param cCode String类型 失败标识
     * @param cMsg String类型 失败说明
     * @return BaseResult
     */
    public static BaseResult fail(String cCode, String cMsg) {
        return new BaseResult(false, cCode, cMsg);
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
