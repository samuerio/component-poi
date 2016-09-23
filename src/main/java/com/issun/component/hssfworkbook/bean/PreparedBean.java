package com.issun.component.hssfworkbook.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * Bean数据预处理对象
 * 
 * @author ZHe
 */

public class PreparedBean {
	
    // ------------------------------------------------------- Instance Variables
	
	/**
	 * 所转化的类对象的全名
	 */
	private String beanName ;
	
	/**
	 * 字段包裹对象列表
	 */
	private List<FieldWrap> fieldWraps;
	
	/**
	 * bean对象数据源列表
	 */
	private List<ArrayList<String>> beanMetas;
	
    // ------------------------------------------------------- Public Methods
	
	
	public List<FieldWrap> getFieldWraps() {
		return fieldWraps;
	}
	
	public void setFieldWraps(List<FieldWrap> fieldWraps) {
		this.fieldWraps = fieldWraps;
	}
	
	public List<ArrayList<String>> getBeanMetas() {
		return beanMetas;
	}
	
	public void setBeanMetas(List<ArrayList<String>> beanMetas) {
		this.beanMetas = beanMetas;
	}
	
	public String getBeanName() {
		return beanName;
	}
	
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
}
