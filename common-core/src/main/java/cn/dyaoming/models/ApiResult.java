package cn.dyaoming.models;

import java.io.Serializable;

public class ApiResult  implements Serializable{

	private boolean flag;
	private String code;
	private String message;
	
	
	
	public ApiResult(){
		setFlag(true);
		setCode("0000");
		setMessage("业务执行成功");	
	}
	
	
	
	public ApiResult(boolean cFlag, String cCode)
	{
		
		setFlag(cFlag);
		setCode(cCode);
		setMessage(cCode);//TODO message 未转换
	}
	
	
	
	public ApiResult(boolean cFlag, String cCode, String cMsg)
	{
		
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
