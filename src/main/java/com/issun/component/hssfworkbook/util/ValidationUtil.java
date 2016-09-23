package com.issun.component.hssfworkbook.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.issun.component.hssfworkbook.annotation.ValidationRule;
import com.issun.component.hssfworkbook.bean.FieldValidationRs;
import com.issun.component.hssfworkbook.bean.type.ValidationType;

/**
 * 校验辅助函数{配置FieldValidationAnnotation注解使用}
 * 
 * @author ZHe
 */
public class ValidationUtil {
	
    // ------------------------------------------------------- Static Variables
	
	private static final String RequiredMsg = "不允许为空";
	private static final String EmailMsg = "请输入正确格式的电子邮件";
	private static final String TelephoneMsg = "请输入正确格式的手机号或座机";

    // ------------------------------------------------------- Public Methods
	
	/**
	 * 按规则校验字段
	 * @param fieldDesc 字段描述名
	 * @param fieldVal 字段值
	 * @param fieldValidation 
	 * @return FieldValidationRs 字段校验结果对象
	 */
	public static FieldValidationRs validate(String fieldDesc, String fieldVal,
			ValidationRule fieldValidation) {
		
		FieldValidationRs fieldValidationRs = new FieldValidationRs();
		
		boolean idRequired = fieldValidation.isRequire();
		//进行非空判断
		if(idRequired && StringUtils.isEmpty(fieldVal)){
			fieldValidationRs.setErrorMsg(RequiredMsg);
			fieldValidationRs.setFieldDesc(fieldDesc);
			fieldValidationRs.setSuccess(false);
			return fieldValidationRs;
		}
		
		ValidationType validationType = fieldValidation.validationType();
		if(!StringUtils.isEmpty(fieldVal) && ValidationType.None != validationType){
			switch(validationType){
				case Email :
					fieldValidationRs = emailValidate(fieldVal);break;
				case Telephone :
					fieldValidationRs = telValidate(fieldVal);break;
				default :
					break;
			}
		}else{
			fieldValidationRs.setSuccess(true);
		}
	    fieldValidationRs.setFieldDesc(fieldDesc);
		
		return fieldValidationRs;
	}
	
    // ------------------------------------------------------- Private Methods
	
	/**
	 * 邮箱校验
	 * @param fieldVal 字段值
	 * @return FieldValidationRs 字段校验结果对象
	 */
	private static FieldValidationRs emailValidate(String fieldVal){
		FieldValidationRs fieldValidationRs = new FieldValidationRs();
		if(!Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$",
						fieldVal))
		{
			fieldValidationRs.setErrorMsg(EmailMsg);
			fieldValidationRs.setSuccess(false);
		}else{
			fieldValidationRs.setSuccess(true);
		}
		return  fieldValidationRs;
	}
	
	/**
	 * 手机格式校验
	 * @param fieldVal 字段值
	 * @return FieldValidationRs 字段校验结果对象
	 */
	private static FieldValidationRs telValidate(String fieldVal){
		
		FieldValidationRs fieldValidationRs = new FieldValidationRs();
		//if(!Pattern.matches("^1[3,4,5,7,8]{1}[0-9]{1}[0-9]{8}$", fieldVal))
		if(!Pattern.matches("^((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$", fieldVal)){
				fieldValidationRs.setErrorMsg(TelephoneMsg);
				fieldValidationRs.setSuccess(false);
			}else{
				fieldValidationRs.setSuccess(true);
			}
		return  fieldValidationRs;
	}
	

}
