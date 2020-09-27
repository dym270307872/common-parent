package cn.dyaoming.enums;


public enum ResultEnum {

    SUCCESS("0000", "成功！"), FAILED("9999", "服务器开小差，请稍后再试或联系管理员！");

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
