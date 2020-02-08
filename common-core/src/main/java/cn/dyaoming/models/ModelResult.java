package cn.dyaoming.models;


/**
 * <p>
 * 带结果的返回集合
 * </p>
 * 
 * @param <E> 实体类型
 * @author DYAOMING
 * @since 2019-04-17
 * @version 0.0.3
 */
public class ModelResult<E> extends BaseResult {

    private static final long serialVersionUID = 1L;

    private E entity;



    /**
     * 默认构造函数
     */
    public ModelResult() {
        super();
    }



    /**
     * 基础构造函数
     * 
     * @param flag boolean类型 结果标志
     * @param code String类型 结果编码
     */
    public ModelResult(boolean flag, String code) {
        super(flag, code);
    }



    /**
     * 带结果说明的构造函数
     * 
     * @param flag boolean类型 结果标志
     * @param code String类型 结果编码
     * @param message String类型 结果说明
     */
    public ModelResult(boolean flag, String code, String message) {
        super(flag, code, message);
    }



    /**
     * 结果初始化的构造函数
     * 
     * @param entity 结果内容实体
     */
    public ModelResult(E entity) {
        super();
        setEntity(entity);
    }



    public E getEntity() {
        return entity;
    }



    public void setEntity(E entity) {
        this.entity = entity;
    }
}
