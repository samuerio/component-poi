package com.issun.component.hssfworkbook.bean;

import java.util.List;


/**
 * Excel内容校验结果对象
 * 
 * @author ZHe
 */

public class ValidationRs {
	
    // ------------------------------------------------------- Instance Variables
	
	/**
	 * Excel校验是否成功的标识
	 */
	private boolean isSuccess ;
	
	/**
	 * Excel每行内容的校验结果
	 */
	private List<BeanValidationRs> beanValidationRsList;
	
    // ------------------------------------------------------- Public Methods

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<BeanValidationRs> getbeanValidationRsList() {
		return beanValidationRsList;
	}

	public void setbeanValidationRsList(List<BeanValidationRs> beanValidationRs) {
		this.beanValidationRsList = beanValidationRs;
	}
	
}
