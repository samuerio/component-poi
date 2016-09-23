package com.issun.component.hssfworkbook.bean;


/**
 * 字段校验结果对象
 * 
 * @author ZHe
 */

public class FieldValidationRs {
	
    // ------------------------------------------------------- Instance Variables
	
	/**
	 * 字段描述名
	 */
	private String fieldDesc;
	
	/**
	 * 字段校验是否成功标识
	 */
	private boolean isSuccess;
	
	/**
	 * 字段值校验失败提示,当isSuccess为false时有值
	 */
	private String errorMsg;
	
    // ------------------------------------------------------- Public Methods

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
