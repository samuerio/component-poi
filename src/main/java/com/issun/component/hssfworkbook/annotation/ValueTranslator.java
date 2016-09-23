package com.issun.component.hssfworkbook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段值转化标注
 * 
 * @author ZHe
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValueTranslator {

	/**
	 * 实现ITranslator接口的类的名称
	 * @return
	 */
	String implClassName();

}
