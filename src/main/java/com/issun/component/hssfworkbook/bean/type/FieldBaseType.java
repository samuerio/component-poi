package com.issun.component.hssfworkbook.bean.type;

/**
 * 字段基本类型
 * 
 * @author ZHe
 */

public enum FieldBaseType {
	
	STRING("java.lang.String"),INT("int"),BOOLEAN("boolean");
	
	String typeClassName;
	
	FieldBaseType(String typeClassName){
		this.typeClassName = typeClassName;
	}
	
	public String getTypeClassName(){
		return this.typeClassName;
	}

	public static FieldBaseType getType(String fieldTypeName) {
		
		if("java.lang.String".endsWith(fieldTypeName)){
			return FieldBaseType.STRING;
		}
		if("int".endsWith(fieldTypeName)){
			return FieldBaseType.INT;
		}
		if("boolean".endsWith(fieldTypeName)){
			return FieldBaseType.BOOLEAN;
		}
		
		return null;
	}
}
