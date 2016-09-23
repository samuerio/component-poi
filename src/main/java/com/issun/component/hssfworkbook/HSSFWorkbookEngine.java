package com.issun.component.hssfworkbook;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.google.gson.reflect.TypeToken;
import com.issun.component.hssfworkbook.annotation.ExcelMapping;
import com.issun.component.hssfworkbook.annotation.ValidationRule;
import com.issun.component.hssfworkbook.annotation.ValueTranslator;
import com.issun.component.hssfworkbook.bean.FieldWrap;
import com.issun.component.hssfworkbook.bean.PreparedBean;
import com.issun.component.hssfworkbook.bean.type.ExcelModeType;
import com.issun.component.hssfworkbook.util.CellStyleUtil;
import com.issun.component.hssfworkbook.util.GsonUtil;

/**
 * Excel管理引擎
 * 
 * @author ZHe
 */
public class HSSFWorkbookEngine {
	
    // ------------------------------------------------------- Public Methods
	
	/**
	 * 将类的字段描述打印为Excel表头.
	 * @param beanClass 类的字节码
	 * @return HSSFWorkbook Excel的Java抽象
	 */
	public static HSSFWorkbook printHeader(Class<?> beanClass){
		
		HSSFWorkbook hsWorkbook = new HSSFWorkbook();
		return printHeader(beanClass, hsWorkbook, 0, 0);
	}
	
	/**
	 * 打印类的Excel表头.
	 * @param beanClass 类的字节码
	 * @param hSSFWorkbook Excel的Java抽象
	 * @param sheetIndex 页签序列{从0开始}
	 * @param headerIndex 打印的表头的序列{从0开始}
	 * @return HSSFWorkbook 打印后的Excel对象
	 */
	public static HSSFWorkbook printHeader(Class<?> beanClass,HSSFWorkbook hSSFWorkbook,
			int sheetIndex,int headerIndex){
		
		HSSFSheet hssfSheet = hSSFWorkbook.createSheet();
		HSSFRow header = hssfSheet.createRow(headerIndex);
		
		int index = 0;
		
		//根据字段包裹对象进行表头生成
		List<FieldWrap> fieldWraps = getFieldWrapList(beanClass);
		for (FieldWrap fieldWrap : fieldWraps) {
			
			if(null == fieldWrap){
				continue;
			}
			
			HSSFCellStyle renderCellStyle = null;//Excel表格渲染样式
			
			ValidationRule validationRule = fieldWrap.getValidation();
			if(validationRule.isRequire()){
				renderCellStyle = CellStyleUtil.getRequire(hSSFWorkbook);
			}else{
				renderCellStyle = CellStyleUtil.getNormal(hSSFWorkbook);
			}
				
			HSSFCell cell = header.createCell(index);
			cell.setCellValue(fieldWrap.getFieldDesc());
			cell.setCellStyle(renderCellStyle);
			
			++ index;
		}
		return hSSFWorkbook;
	}
	
	
	/**
	 * 打印Excel内容
	 * @param beanClass 类的字节码
	 * @param beanList bean对象列表
	 * @param hSSFWorkbook Excel对象
	 * @param sheetIndex 页签序列(从0开始)
	 * @param bodyIndex 内容开始的行号
	 * @return HSSFWorkbook 打印后的Excel对象
	 */
	public static HSSFWorkbook printBody(Class<?> beanClass,List<Object> beanList,HSSFWorkbook hSSFWorkbook,
			int sheetIndex,int bodyIndex){
		
		List<FieldWrap> fieldWrapList = getFieldWrapList(beanClass);
		String beanListJson = GsonUtil.getJsonStr(beanList);
		List<Map<String,Object>> beanMapList = GsonUtil.jsonToBean(beanListJson, 
										new TypeToken<List<Map<String, Object>>>(){}.getType());
		
		
		HSSFSheet hssfSheet = hSSFWorkbook.getSheetAt(sheetIndex);
		int rowIndex = bodyIndex;
		for (Map<String,Object> beanMap : beanMapList) {
			HSSFRow row = hssfSheet.createRow(rowIndex);
			
			for(int column = 0; column < fieldWrapList.size(); ++column){
				FieldWrap fieldWrap = fieldWrapList.get(column);
				ITranslator translator = fieldWrap.getTranslator();
				
				String fieldName = fieldWrap.getFieldName();
				Object fieldValue = beanMap.get(fieldName);
				
				if(null != translator){//有翻译实现类
					fieldValue = translator.getTransValue(fieldValue, ExcelModeType.EXPORT);
				}
				if(null != fieldValue){
					row.createCell(column).setCellValue((String)fieldValue);
				}
			}
		}
		return hSSFWorkbook;
	}
	
	/**
	 * 解析Excel内容
	 * @param beanClass 类字节码
	 * @param hssfWorkbook Excel的Java抽象
	 * @param sheetIndex 页签序列{从0开始}
	 * @param bodyIndex 需要解析的内容序号{从0开始}
	 * @param headerIndex 打印的表头的序列{从0开始}
	 * @return BeanProcesser Excel内容的预处理对象
	 */
	public static PreparedBean parseBody(Class<?> beanClass,HSSFWorkbook hssfWorkbook,
			int sheetIndex,int bodyIndex,int headerIndex){
		
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetIndex);
		
		//获取字段的包裹对象{其中包含字段校验规则,字段写入方法}
		List<FieldWrap> fieldWraps = getFieldWrapList(beanClass);
		
		//解析Row为BeanMeta
		List<ArrayList<String>> beanMetas =  new ArrayList<ArrayList<String>>();
		int rowSize = hssfSheet.getLastRowNum();
		int columnSize = fieldWraps.size();
		for(int rowIndex = bodyIndex; rowIndex <= rowSize; ++rowIndex){
			ArrayList<String> beanMeta = new ArrayList<String>();
			
			HSSFRow row = hssfSheet.getRow(rowIndex);
			for(int column = 0; column < columnSize; ++column){
				HSSFCell curCell = row.getCell(column);
				beanMeta.add(getCellValue(curCell));
			}
			beanMetas.add(beanMeta);
		}
		
		//Bean包裹对象
		PreparedBean preparedBean = new PreparedBean();beanClass.getName();
		preparedBean.setBeanName(beanClass.getName());
		preparedBean.setFieldWraps(fieldWraps);
		preparedBean.setBeanMetas(beanMetas);
		
		return preparedBean;
	}
	
    // ------------------------------------------------------- Private Methods
	

	/**
	 * 获取经过排序的字段包装对象{包装了校验、描述信息}
	 * @param beanClass 类字节码
	 * @return List<FieldWrap>
	 */
	private static List<FieldWrap> getFieldWrapList(Class<?> beanClass) {
		
		Field[] fields = beanClass.getDeclaredFields();
		List<FieldWrap> fieldWraps = new ArrayList<FieldWrap>();
		FieldWrap [] arrFieldWrap = new FieldWrap [fields.length]; 
		
		//获取对应字段的HSSFWorkbook注解对象
		for (Field field : fields) {
			
			ExcelMapping excelMapping = 
					field.getAnnotation(ExcelMapping.class);
			ValidationRule validationRule = 
					field.getAnnotation(ValidationRule.class);
			ValueTranslator transformHelperAnno = 
					field.getAnnotation(ValueTranslator.class);
			if(null == excelMapping){
				continue;
			}
			
			int index = excelMapping.index();
			String fieldDesc = excelMapping.desc();
			String fieldName = field.getName();
			Method fieldWriter = null; 
			try {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName,beanClass);
				fieldWriter = propertyDescriptor.getWriteMethod();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			
			FieldWrap fieldWrap = new FieldWrap();
			fieldWrap.setFieldName(fieldName);
			fieldWrap.setFieldDesc(fieldDesc);
			fieldWrap.setFieldWriter(fieldWriter);
			fieldWrap.setFieldType(field.getType());
			if(null != validationRule){//若有校验
				fieldWrap.setValidation(validationRule);
			}
			if(null != transformHelperAnno){//若有转化辅助函数
				String implClassName = transformHelperAnno.implClassName();
				try {
					ITranslator translator = (ITranslator) Class.forName(implClassName).newInstance();
					fieldWrap.setTranslator(translator);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			
			arrFieldWrap[index] = fieldWrap;
		}
		
		//过滤数组arrFieldWrap的空元素
		for (FieldWrap fieldWrap : arrFieldWrap) {
			if(null == fieldWrap){
				continue;
			}
			fieldWraps.add(fieldWrap);
		}
		return fieldWraps;
	}
	
	/**
	 * 获取表格Cell的文本值
	 * @param cell
	 * @return value
	 */
	private static String getCellValue(HSSFCell cell) {
		String value = "";
		if(null != cell){
			cell.setCellType(Cell.CELL_TYPE_STRING);
			value = cell.toString().trim();//过滤前后空格
		}
		return value;
	}
	
}
