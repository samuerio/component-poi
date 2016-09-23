package com.issun.component.hssfworkbook;

import com.issun.component.hssfworkbook.bean.type.ExcelModeType;


/**
 * 实现输出值翻译的接口
 * 
 * @author ZHe
 */

public interface ITranslator {
	
	/**
	 * 翻译工具方法
	 * @param value 需要转化的值
	 * @param excelModeType Excel模式{导入、导出}
	 * @return Object 转化后的对象
	 */
	public Object getTransValue(Object value,ExcelModeType excelModeType);
	
}
