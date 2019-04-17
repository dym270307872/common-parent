package cn.dyaoming.models;


/**
 * <p>
 * 数据结果集
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-17
 * @version V1.0
 */
public class DataResult extends ApiResult {

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



	public Object getData() {
		return data;
	}



	public void setData(Object data) {
		this.data = data;
	}
}
