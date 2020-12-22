package cn.dyaoming.enums;


/**
 * <p>
 * rest默认状态码枚举
 * </p>
 * 
 * @author DYAOMING
 * @since 2020/12/22
 * @version 0.0.1
 */
public enum ResultEnum {

    /**
     * 成功状态码
     */
    SUCCESS("00000", "成功！"),
    /**
     * 失败状态码
     */
    FAILED("99999", "服务器开小差，请您稍后再试！");

    ResultEnum() {

    }



    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;



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
