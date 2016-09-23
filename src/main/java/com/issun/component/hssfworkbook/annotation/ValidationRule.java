package com.issun.component.hssfworkbook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.issun.component.hssfworkbook.bean.type.ValidationType;


/**
 * 字段校验注解类
 * 
 * @author ZHe
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidationRule {
	
	/**
	 * 是否必填
	 * @return
	 */
	public boolean isRequire() default false;

	/**
	 * 校验规则
	 * @return
	 */
	public ValidationType validationType() default ValidationType.None;
	
}
