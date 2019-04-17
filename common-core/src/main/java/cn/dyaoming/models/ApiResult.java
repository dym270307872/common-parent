package cn.dyaoming.models;


import java.io.Serializable;


/**
 * <p>
 * 默认返回结果集
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-17
 * @version V1.0
 */
public class ApiResult implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private boolean				flag;
	private String				code;
	private String				message;



	/**
	 * 默认构造函数
	 */
	public ApiResult() {
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
	public ApiResult(boolean cFlag, String cCode) {

		setFlag(cFlag);
		setCode(cCode);
		setMessage(cCode);// TODO message 未转换
	}



	/**
	 * 带结果说明的构造函数
	 * 
	 * @param cFlag boolean类型 结果标志
	 * @param cCode String类型 结果编码
	 * @param cMsg String类型 结果说明
	 */
	public ApiResult(boolean cFlag, String cCode, String cMsg) {

		setFlag(cFlag);
		setCode(cCode);
		setMessage(cMsg);
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
