package com.issun.component.hssfworkbook.bean.type;

/**
 * 校验类型
 * 
 * @author ZHe
 */

public enum ValidationType {
	
	Email(0), Telephone(1), None(2);
	
	int type;
	
	private ValidationType(int type){
		this.type = type;
	} 
	
	public int getValue(){
		return this.type;
	}
	
	
}
