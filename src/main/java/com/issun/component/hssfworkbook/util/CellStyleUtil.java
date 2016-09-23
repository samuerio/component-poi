package com.issun.component.hssfworkbook.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 默认的Cell样式
 * 
 * @author ZHe
 */

public class CellStyleUtil {
	
    // ------------------------------------------------------- Public Methods

	/**
	 * 表头Cell样式为：11号、粗体、字体红色；上下、水平区中
	 * @param hSSFWorkbook
	 * @return HSSFCellStyle
	 */
	public static HSSFCellStyle getRequire(HSSFWorkbook hSSFWorkbook) {
		
		HSSFCellStyle requiredInfoStyle = hSSFWorkbook.createCellStyle();
		HSSFFont requiredInfoFont = hSSFWorkbook.createFont();
		
		requiredInfoFont.setColor(HSSFFont.COLOR_RED);//必填用户信息显示红色
		requiredInfoFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//必填用户信息显示粗体
		short fontHeight = 11;
		requiredInfoFont.setFontHeightInPoints(fontHeight);
		requiredInfoStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//
		requiredInfoStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		requiredInfoStyle.setFont(requiredInfoFont);
		
		return requiredInfoStyle;
	}

	/**
	 * 普通Cell样式：上下、水平区中
	 * @param hSSFWorkbook
	 * @return HSSFCellStyle
	 */
	public static HSSFCellStyle getNormal(HSSFWorkbook hSSFWorkbook) {
		
		HSSFCellStyle normalInfoStyle = hSSFWorkbook.createCellStyle();
		
		normalInfoStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		normalInfoStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		return normalInfoStyle;
	}
	
	

}
