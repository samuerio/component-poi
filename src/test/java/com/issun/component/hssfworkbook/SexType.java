package com.issun.component.hssfworkbook;

/**
 * 性别枚举
 */
public enum SexType {
	
	MAN(0),WOMAN(1);
	
	private int type;
	
	SexType(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
	
}
