package com.issun.component.hssfworkbook.util;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Gson工具方法类
 * 
 * @author ZHe
 *
 */
public class GsonUtil {
	
	//------------------------------------------------------- Static Variables

	private static final String DEFAULT_CHARSET = "UTF-8";
	
	private static final String MIME = "text/html;charset=" + DEFAULT_CHARSET;
	
	private static final Log LOG = LogFactory.getLog(GsonUtil.class);
	
	//------------------------------------------------------- Public Methods

	/**
	 * 将bean打印到Response的内容
	 * @param response
	 * @param bean
	 */
	public static void printJsonBean(HttpServletResponse response, Object bean) {
		if(null == bean){
			return;
		}
		
		Gson gson = new Gson();
		response.setContentType(MIME);
		response.setCharacterEncoding(DEFAULT_CHARSET);
		
		try {
			response.getWriter().write(gson.toJson(bean));
		} catch (IOException e) {
			//TODO 错误日志
			LOG.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Json的字符串
	 * @param obj 对象
	 * @return    json字符串
	 */
	public static String getJsonStr(Object obj){
		Gson gson = new Gson();
		String resultStr = gson.toJson(obj);
		gson = null;
		return resultStr;
	}
	
	/**
	 * JSON转Bean
	 * @param sourceJson  源JSON字符串
	 * @param type        类对象的类型
	 * @return            类对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String sourceJson, Type type) {
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement jse = jsonParser.parse(sourceJson);
		T obj = (T) gson.fromJson(jse, type);
		gson = null;
		return obj;
	}

}
