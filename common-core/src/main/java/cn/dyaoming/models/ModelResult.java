package cn.dyaoming.models;

import java.io.Serializable;

public class ModelResult<E> extends ApiResult {

	private E entity;

	public ModelResult(){
		super();
	}
	
	public ModelResult(boolean cFlag, String cCode){
		super(cFlag,cCode);
	}
	
	public ModelResult(boolean cFlag, String cCode, String message){
		super(cFlag,cCode,message);
	}

	public ModelResult(E entity){
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
