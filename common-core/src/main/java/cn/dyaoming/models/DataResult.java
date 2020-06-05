package cn.dyaoming.models;


/**
 * <p>
 * 数据结果集
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-17
 * @version 0.0.3
 */
public class DataResult extends BaseResult {

    private static final long serialVersionUID = 1L;
    private Object data;



    /**
     * 默认构造函数
     */
    public DataResult() {
        super();
    }



    /**
     * 基础构造函数
     * 
     * @param flag boolean类型 结果标志
     * @param code String类型 结果编码
     */
    public DataResult(boolean flag, String code) {
        super(flag, code);
    }



    /**
     * 带结果说明的构造函数
     * 
     * @param flag boolean类型 结果标志
     * @param code String类型 结果编码
     * @param msg String类型 结果说明
     */
    public DataResult(boolean flag, String code, String msg) {
        super(flag, code, msg);
    }



    /**
     * 基础构造函数
     * 
     * @param data boolean类型 结果标志
     */
    public DataResult(Object data) {
        super();
        setData(data);
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
     * 成功
     * </p>
     * 
     * @param data 数据
     * @return BaseResult
     */
    public static BaseResult success(Object data) {
        return new DataResult(data);
    }



    /**
     * <p>
     * 失败
     * </p>
     * 
     * @param code String类型 失败标识
     * @param msg String类型 失败说明
     * @return BaseResult
     */
    public static BaseResult fail(String code, String msg) {
        return new BaseResult(false, code, msg);
    }



    public Object getData() {
        return data;
    }



    public void setData(Object data) {
        this.data = data;
    }
}
