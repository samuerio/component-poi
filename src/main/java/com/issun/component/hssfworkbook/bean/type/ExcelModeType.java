package com.issun.component.hssfworkbook.bean.type;

/**
 * Excel模式：导入和导出
 * 
 * @author ZHe
 */
public enum ExcelModeType {
	
	IMPORT(0),EXPORT(1);
	
	private int type;
	
	ExcelModeType(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
}
