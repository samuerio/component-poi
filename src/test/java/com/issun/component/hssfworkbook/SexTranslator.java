package com.issun.component.hssfworkbook;

import com.issun.component.hssfworkbook.bean.type.ExcelModeType;

/**
 *  性别翻译工具
 */
public class SexTranslator implements ITranslator {

	private static final Object MAN_DESC = "男";
	private static final Object WOMAN_DESC = "女";
	

	@Override
	public Object getTransValue(Object value, ExcelModeType hssfModeType) {
		
		Object transformValue = null;
		
		switch(hssfModeType){
			case IMPORT:
				transformValue = getImportTransVal(value);
				break;	
			case EXPORT:
				transformValue = getExportTransVal(value);
				break;
			default:
				break;
		}
		
		return transformValue;
	}

	private Object getExportTransVal(Object value) {
		
		if(((Integer)value).intValue() == SexType.MAN.getType()){
			return MAN_DESC;
		}else if(((Integer)value).intValue() == SexType.MAN.getType()){
			return WOMAN_DESC;
		}
		return null;
	}

	private Object getImportTransVal(Object value) {
		Object transformValue = null;
		String sexDesc = (String)value;
		
		if(MAN_DESC.equals(sexDesc)){
			transformValue = SexType.MAN;
		}
		
		if(WOMAN_DESC.equals(sexDesc)){
			transformValue = SexType.WOMAN;
		}
		
		return transformValue;
	}

}
