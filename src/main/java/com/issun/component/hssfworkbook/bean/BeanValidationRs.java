package com.issun.component.hssfworkbook.bean;

import java.util.List;


/**
 * Excel每行Bean的校验结果对象
 * 
 * @author ZHe
 */

public class BeanValidationRs {
	
    // ------------------------------------------------------- Instance Variables
	
	/**
	 * 校验成功的标识
	 */
	private boolean isSuccess ;
	
	/**
	 * 字段校验结果列表
	 */
	private List<FieldValidationRs> fieldValidationRsList;
	
    // ------------------------------------------------------- Public Methods

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<FieldValidationRs> getFieldValidationRsList() {
		return fieldValidationRsList;
	}

	public void setFieldValidationRsList(
			List<FieldValidationRs> fieldValidationRsList) {
		this.fieldValidationRsList = fieldValidationRsList;
	}
	
}
