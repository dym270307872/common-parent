package cn.dyaoming.models;


import java.io.Serializable;

import cn.dyaoming.enums.ResultEnum;


/**
 * <p>
 * 带结果的返回集合
 * </p>
 * 
 * @author DYAOMING
 * @since 2020-09-17
 * @version 0.0.5
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BaseRestModel<E> implements Serializable {

    private static final long serialVersionUID = -823816080654450979L;

    private boolean success;
    private String code;
    private String message;
    private E data;



    /**
     * 默认构造函数
     */
    public BaseRestModel() {
        this.success = true;
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
    }



    /**
     * 基础构造函数
     * 
     * @param success boolean类型 结果标志
     * @param message String类型 结果说明
     */
    public BaseRestModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }



    /**
     * 带结果说明的构造函数
     * 
     * @param success boolean类型 结果标志
     * @param code String类型 结果编码
     * @param message String类型 结果说明
     */
    public BaseRestModel(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }



    /**
     * 结果初始化的构造函数
     * 
     * @param data 实体内容类
     */
    public BaseRestModel(E data) {
        this();
        setData(data);
    }



    /**
     * <p>
     * 成功对象静态方法
     * </p>
     * 
     * @return BaseRestModel对象
     */
    public static BaseRestModel success() {
        return new BaseRestModel();
    }



    /**
     * <p>
     * 成功对象静态方法
     * </p>
     * 
     * @param data 数据内容
     * @return BaseRestModel对象
     */
    public static <E> BaseRestModel<E> success(String message) {
        return new BaseRestModel(true, message);
    }



    /**
     * <p>
     * 成功对象静态方法
     * </p>
     * 
     * @param data 数据内容
     * @return BaseRestModel对象
     */
    public static <E> BaseRestModel<E> success(String code, String message) {
        return new BaseRestModel(true, code, message);
    }



    /**
     * <p>
     * 成功对象静态方法
     * </p>
     * 
     * @param data 数据内容
     * @return BaseRestModel对象
     */
    public static <E> BaseRestModel<E> success(E data) {
        return new BaseRestModel(data);
    }



    /**
     * <p>
     * 失败对象静态方法
     * </p>
     * 
     * @return BaseRestModel对象
     */
    public static BaseRestModel failed() {
        return new BaseRestModel(false, ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage());
    }



    /**
     * <p>
     * 失败对象静态方法
     * </p>
     * 
     * @return BaseRestModel对象
     */
    public static BaseRestModel failed(String message) {
        return new BaseRestModel(false, message);
    }



    /**
     * <p>
     *  失败对象静态方法
     * </p>
     * 
     * @param code 错误码
     * @param message 错误说明
     * @return BaseRestModel对象
     */
    public static BaseRestModel failed(String code, String message) {
        return new BaseRestModel(false, code, message);
    }



    public boolean isSuccess() {
        return success;
    }



    public void setSuccess(boolean success) {
        this.success = success;
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



    public E getData() {
        return data;
    }



    public void setData(E data) {
        this.data = data;
    }
}
