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

	private static final long	serialVersionUID	= 1L;
	private Object				data;



	/**
	 * 默认构造函数
	 */
	public DataResult() {
		super();
	}



	/**
	 * 基础构造函数
	 * 
	 * @param cFlag boolean类型 结果标志
	 * @param cCode String类型 结果编码
	 */
	public DataResult(boolean cFlag, String cCode) {
		super(cFlag, cCode);
	}



	/**
	 * 带结果说明的构造函数
	 * 
	 * @param cFlag boolean类型 结果标志
	 * @param cCode String类型 结果编码
	 * @param cMsg String类型 结果说明
	 */
	public DataResult(boolean cFlag, String cCode, String cMsg) {
		super(cFlag, cCode, cMsg);
	}

	/**
     * 基础构造函数
     * 
     * @param cData boolean类型 结果标志
     * @param cCode String类型 结果编码
     */
    public DataResult(Object cData) {
        super();
        setData(cData);
    }
	
	
	/**
     * <p>成功</p>
     * @return BaseResult
     */
    public static BaseResult success() {
        return new BaseResult();
    }
    
    /**
     * <p>成功</p>
     * @return BaseResult
     */
    public static BaseResult success(Object cData) {
        return new DataResult(cData);
    }
    
    
    /**
     * <p>失败</p>
     * @param cCode String类型 失败标识
     * @param cMsg String类型 失败说明
     * @return BaseResult
     */
    public static BaseResult fail(String cCode, String cMsg) {
        return new BaseResult(false,cCode,cMsg);
    }


	public Object getData() {
		return data;
	}



	public void setData(Object data) {
		this.data = data;
	}
}
