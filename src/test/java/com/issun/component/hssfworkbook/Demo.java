package com.issun.component.hssfworkbook;


import com.issun.component.hssfworkbook.annotation.ValidationRule;
import com.issun.component.hssfworkbook.annotation.ExcelMapping;
import com.issun.component.hssfworkbook.bean.type.ValidationType;

public class Demo {
			
	/**
	 * 联系人名
	 */
	
	@ValidationRule (isRequire=true)
	@ExcelMapping (desc="姓名",index=0)
	private String lianxirenming  = "" ;
		
	private String unid  = "" ;
		
	/**
	 * 备注
	 */
	@ExcelMapping (desc="备注",index=9)
	private String beizhu  = "" ;
		
	/**
	 * 单位电话
	 */
	@ValidationRule (validationType = ValidationType.Telephone)
	@ExcelMapping (desc="单位电话",index=8)
	private String danweidianhua  = "" ;
		
	/**
	 * 家庭电话
	 */
	@ValidationRule (validationType = ValidationType.Telephone)
	@ExcelMapping (desc="家庭电话",index=7)
	private String jiatingdianhua  = "" ;
		
	/**
	 * 家庭住址
	 */
	@ExcelMapping (desc="家庭地址",index=4)
	private String jiatingzhuzhi  = "" ;
		
	/**
	 * 分类
	 */
	@ValidationRule (isRequire=true)
	@ExcelMapping (desc="分类",index=6)
	private String fenlei  = "" ;
		
	/**
	 * 单位地址
	 */
	@ExcelMapping (desc="单位地址",index=5)
	private String danweidizhi  = "" ;
		
	/**
	 * 电子邮箱
	 */
	@ValidationRule (validationType = ValidationType.Email,isRequire = true)
	@ExcelMapping (desc="电子邮箱",index=3)
	private String dianziyouxiang  = "" ;
		
	/**
	 * 手机号码
	 */
	@ValidationRule (validationType = ValidationType.Telephone,isRequire=true)
	@ExcelMapping (desc="手机号码",index=2)
	private String shoujihaoma  = "" ;
		
	/**
	 * 单位标识
	 */
	private String danweibiaoshi  = "" ;
		
	/**
	 * 单位名称
	 */
	@ValidationRule (isRequire=true)
	@ExcelMapping (desc="单位名称",index=1)
	private String danweimingcheng  = "" ;
	
	/**
	 * 文档状态（0:作废：1：正常）
	 */
	private String state = "1";
		
	/**
	 * 获取联系人名
	 * @return String
	 */
	public String getLianxirenming() {
		return lianxirenming;
	}

	/**
	 * 设置联系人名
	 * @param lianxirenming
	 *               String 联系人名
	 */
	public void setLianxirenming(String lianxirenming) {
		this.lianxirenming = lianxirenming;
	}
	
	/**
	 * 获取
	 * @return String
	 */
	public String getUnid() {
		return unid;
	}

	/**
	 * 设置
	 * @param unid
	 *               String 
	 */
	public void setUnid(String unid) {
		this.unid = unid;
	}
	
	/**
	 * 获取备注
	 * @return String
	 */
	public String getBeizhu() {
		return beizhu;
	}

	/**
	 * 设置备注
	 * @param beizhu
	 *               String 备注
	 */
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	/**
	 * 获取单位电话
	 * @return String
	 */
	public String getDanweidianhua() {
		return danweidianhua;
	}

	/**
	 * 设置单位电话
	 * @param danweidianhua
	 *               String 单位电话
	 */
	public void setDanweidianhua(String danweidianhua) {
		this.danweidianhua = danweidianhua;
	}
	
	/**
	 * 获取家庭电话
	 * @return String
	 */
	public String getJiatingdianhua() {
		return jiatingdianhua;
	}

	/**
	 * 设置家庭电话
	 * @param jiatingdianhua
	 *               String 家庭电话
	 */
	public void setJiatingdianhua(String jiatingdianhua) {
		this.jiatingdianhua = jiatingdianhua;
	}
	
	/**
	 * 获取家庭住址
	 * @return String
	 */
	public String getJiatingzhuzhi() {
		return jiatingzhuzhi;
	}

	/**
	 * 设置家庭住址
	 * @param jiatingzhuzhi
	 *               String 家庭住址
	 */
	public void setJiatingzhuzhi(String jiatingzhuzhi) {
		this.jiatingzhuzhi = jiatingzhuzhi;
	}
	
	/**
	 * 获取分类
	 * @return String
	 */
	public String getFenlei() {
		return fenlei;
	}

	/**
	 * 设置分类
	 * @param fenlei
	 *               String 分类
	 */
	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}
	
	/**
	 * 获取单位地址
	 * @return String
	 */
	public String getDanweidizhi() {
		return danweidizhi;
	}

	/**
	 * 设置单位地址
	 * @param danweidizhi
	 *               String 单位地址
	 */
	public void setDanweidizhi(String danweidizhi) {
		this.danweidizhi = danweidizhi;
	}
	
	/**
	 * 获取电子邮箱
	 * @return String
	 */
	public String getDianziyouxiang() {
		return dianziyouxiang;
	}

	/**
	 * 设置电子邮箱
	 * @param dianziyouxiang
	 *               String 电子邮箱
	 */
	public void setDianziyouxiang(String dianziyouxiang) {
		this.dianziyouxiang = dianziyouxiang;
	}
	
	/**
	 * 获取手机号码
	 * @return String
	 */
	public String getShoujihaoma() {
		return shoujihaoma;
	}

	/**
	 * 设置手机号码
	 * @param shoujihaoma
	 *               String 手机号码
	 */
	public void setShoujihaoma(String shoujihaoma) {
		this.shoujihaoma = shoujihaoma;
	}
	
	/**
	 * 获取单位标识
	 * @return String
	 */
	public String getDanweibiaoshi() {
		return danweibiaoshi;
	}

	/**
	 * 设置单位标识
	 * @param danweibiaoshi
	 *               String 单位标识
	 */
	public void setDanweibiaoshi(String danweibiaoshi) {
		this.danweibiaoshi = danweibiaoshi;
	}
	
	/**
	 * 获取单位名称
	 * @return String
	 */
	public String getDanweimingcheng() {
		return danweimingcheng;
	}

	/**
	 * 设置单位名称
	 * @param danweimingcheng
	 *               String 单位名称
	 */
	public void setDanweimingcheng(String danweimingcheng) {
		this.danweimingcheng = danweimingcheng;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
		
}
