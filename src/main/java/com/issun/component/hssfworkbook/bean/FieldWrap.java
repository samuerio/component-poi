package com.issun.component.hssfworkbook.bean;

import java.lang.reflect.Method;

import com.issun.component.hssfworkbook.ITranslator;
import com.issun.component.hssfworkbook.annotation.ValidationRule;

/**
 * 字段元信息包裹对象
 * 
 * @author ZHe
 */
public class FieldWrap {
	
    // ------------------------------------------------------- Instance Variables
	
	/**
	 * 字段名称
	 */
	private String fieldName;
	
	/**
	 * 字段描述名
	 */
	private String fieldDesc;
	
	/**
	 * 字段写入方法
	 */
	private Method fieldWriter;
	
	/**
	 * 字段所属类型
	 */
	private Class<?> fieldType;
	
	/**
	 * 字段校验
	 */
	private ValidationRule validation;
	
	/**
	 * 字段值转换函数
	 */
	private ITranslator translator;
	
	
    // ------------------------------------------------------- Public Methods
	
	
	public Class<?> getFieldType() {
		return fieldType;
	}
	
	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}
	
	public ValidationRule getValidation() {
		return validation;
	}
	
	public void setValidation(ValidationRule validation) {
		this.validation = validation;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldDesc() {
		return fieldDesc;
	}
	
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	
	public Method getFieldWriter() {
		return fieldWriter;
	}
	
	public void setFieldWriter(Method fieldWriter) {
		this.fieldWriter = fieldWriter;
	}

	public ITranslator getTranslator() {
		return translator;
	}

	public void setTranslator(ITranslator translator) {
		this.translator = translator;
	}
}
