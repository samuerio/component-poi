package com.issun.component.hssfworkbook.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.issun.component.hssfworkbook.ITranslator;
import com.issun.component.hssfworkbook.annotation.ValidationRule;
import com.issun.component.hssfworkbook.bean.BeanValidationRs;
import com.issun.component.hssfworkbook.bean.FieldValidationRs;
import com.issun.component.hssfworkbook.bean.FieldWrap;
import com.issun.component.hssfworkbook.bean.PreparedBean;
import com.issun.component.hssfworkbook.bean.ValidationRs;
import com.issun.component.hssfworkbook.bean.type.ExcelModeType;
import com.issun.component.hssfworkbook.bean.type.FieldBaseType;


/**
 * 预处理对象PrepareBean的通用工具
 * 
 * @author ZHe
 *
 */

public class PrepareBeanUtil {
	
    // ------------------------------------------------------- Public Methods
	
	/**
	 * 按字段校验规则对Bean数据进行校验
	 * @return ValidationRs 校验结果对象
	 */
	public static ValidationRs executeValidate(PreparedBean preparedBean){
		boolean isSuccess = true;
		List<BeanValidationRs> beanValidationRsList = new ArrayList<BeanValidationRs>();
		
		for(ArrayList<String> beanMeta : preparedBean.getBeanMetas()){
			BeanValidationRs beanValidationRs = validateBeanMeta(beanMeta,preparedBean.getFieldWraps());
			if(!beanValidationRs.isSuccess()){
				isSuccess = false;
			}
			beanValidationRsList.add(beanValidationRs);
		}
		
		ValidationRs rs = new ValidationRs();
		rs.setSuccess(isSuccess);
		rs.setbeanValidationRsList(beanValidationRsList);
		
		return rs;
	}
	
	/**
	 * 对BeanMeta数据进行转化为对应的Bean对象
	 * @param BeanCls 目标Bean类全名
	 * @return List<Object>
	 */
	public static List<Object> executeTransform(PreparedBean preparedBean){
		
		List<Object> beans = new ArrayList<Object>();
		
		List<ArrayList<String>> beanMetas = preparedBean.getBeanMetas();
		List<FieldWrap> fieldWraps = preparedBean.getFieldWraps();
		String beanName = preparedBean.getBeanName();
		
		try{
			Iterator<ArrayList<String>> beanMetaItera = beanMetas.iterator();
			while(beanMetaItera.hasNext()){
				ArrayList<String> beanMeta = beanMetaItera.next();
				Object bean = Class.forName(beanName).newInstance();
				
				Iterator<String> metaItera = beanMeta.iterator();
				int curfieldIndex = 0;
				while(metaItera.hasNext()){
					Object fieldMeta = metaItera.next();
					FieldWrap fieldWrap = fieldWraps.get(curfieldIndex);
					
					//根据字段类型进行字段值得转化
					Object fieldMetaTran = getFieldMetaTran(fieldWrap,fieldMeta);
					Method fieldWriter = fieldWrap.getFieldWriter();
					fieldWriter.invoke(bean, fieldMetaTran);
					
					++curfieldIndex;
				}
				beans.add(bean);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return beans;
	}
	
	
    // ------------------------------------------------------- Private Methods
	
	/**
	 * 根据字段类型对fieldMeta进行转化
	 * @param fieldWrap
	 * @param fieldMeta
	 * @return
	 */
	private static Object getFieldMetaTran(FieldWrap fieldWrap ,Object fieldMeta) {
		
		Object fieldMetaTran = null;
		
		ITranslator translator = fieldWrap.getTranslator();
		if(null != translator){//若有自定义翻译接口的实现
			fieldMetaTran = translator.getTransValue(fieldMeta, ExcelModeType.IMPORT);
		}else{//按字段类型进行相应的转化
			String fieldTypeName = fieldWrap.getFieldType().getName();
			FieldBaseType fieldBaseType = FieldBaseType.getType(fieldTypeName);
			if(null == fieldBaseType){
				System.out.println("非基本数据类型，需自定义转化类实现转化接口ITranslator!");
			}else{
				fieldMetaTran = getBaseTypeTrans(fieldBaseType,fieldMeta);
			}
		}
		return fieldMetaTran;
	}
	
	/**
	 * 获取基本类型的转化值
	 * @param fieldBaseType 字段基本类型枚举
	 * @param fieldMeta 字段值
	 * @return Object 转化后的值
	 */
	private static Object getBaseTypeTrans(FieldBaseType fieldBaseType,
			Object fieldMeta) {
		
		Object tranformRs = null;
		switch(fieldBaseType){
			case STRING:
				tranformRs = (String)fieldMeta;
				break;
			case INT:
				tranformRs = Integer.parseInt((String)fieldMeta);
				break;
			case BOOLEAN:
				tranformRs = Boolean.parseBoolean((String)fieldMeta);
				break;
			default:
				break;
		}
		return tranformRs;
	}
	
	
	/**
	 * 对BeanMeta数据进行校验
	 * @param beanMeta
	 * @return BeanValidationRs Bean数据校验结果
	 */
	private static BeanValidationRs validateBeanMeta(ArrayList<String> beanMeta, List<FieldWrap> fieldWraps) {
		boolean isSuccess = true;
		List<FieldValidationRs> fieldValidationRsList = new ArrayList<FieldValidationRs>();
		for(int index = 0; index < beanMeta.size(); ++index){
			String fieldVal = beanMeta.get(index);
			FieldWrap fieldWrap = fieldWraps.get(index);
			
			String fieldDesc = fieldWrap.getFieldDesc();
			ValidationRule fieldValidation = fieldWrap.getValidation();
			FieldValidationRs fieldValidationRs = null;
			if(null != fieldValidation){
				fieldValidationRs = ValidationUtil.validate(fieldDesc,fieldVal,fieldValidation);
			}else{
				fieldValidationRs = new FieldValidationRs();
				fieldValidationRs.setSuccess(true);
			}
			
			if(!fieldValidationRs.isSuccess()){
				isSuccess = false;
			}
			fieldValidationRsList.add(fieldValidationRs);
		}
		
		BeanValidationRs beanValidationRs = new BeanValidationRs();
		beanValidationRs.setFieldValidationRsList(fieldValidationRsList);
		beanValidationRs.setSuccess(isSuccess);
		return beanValidationRs;
	}
	
}
