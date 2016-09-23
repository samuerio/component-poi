package com.issun.component.hssfworkbook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类和Excel的相关映射信息
 * 
 * @author ZHe
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelMapping {
	
	
	/**
	 * 字段描述名
	 * @return
	 */
	public String desc();
	
	/**
	 * 字段输出在Excel中排序号
	 * @return
	 */
	public int index();
	
}
