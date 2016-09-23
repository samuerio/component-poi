package com.issun.component.hssfworkbook;

import com.issun.component.hssfworkbook.annotation.ExcelMapping;
import com.issun.component.hssfworkbook.annotation.ValueTranslator;

public class Demo2 {
	
	@ExcelMapping (index=3,desc="名称")
	String name;
	@ExcelMapping (index=2,desc="序号")
	int num;
	@ExcelMapping (index=1,desc="是否成功")
	boolean success;
	
	//所实现接口的子类
	@ValueTranslator ( implClassName = "com.issun.component.hssfworkbook.SexTranslator" )
	@ExcelMapping (index=0,desc="性别")
	SexType sexType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public SexType getSexType() {
		return sexType;
	}
	public void setSexType(SexType sexType) {
		this.sexType = sexType;
	}
}
