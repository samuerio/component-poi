# Poi-Component

简单好用的、实现了「类」&&「实例集」同Excel映射的Poi组件

# Features

- 导入Excel的校验
- 提供导入&导出值的翻译
- 自定义Excel表头和类的映射顺序

# Quick Start


**1.在输出Excel的类中进行配置**

以字段标注的方式提供相关Excel的配置：

**ExcelMapping**

```java
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
```

配置字段在Excel中显示&位置的信息映射关系(index从0开始)。**注意**：对于没有配置映射关系的字段，不会输出到Excel中。

eg.

```java
/**
	 * 联系人名
	 */
	
	@ValidationRule (isRequire=true)
	@ExcelMapping (desc="姓名",index=0)
	private String lianxirenming  = "" ;
```
这个字段输出到Excel时，显示为“姓名”，并且位于第一列。


**ValidationRule**

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidationRule {
	
	/**
	 * 是否必填
	 * @return
	 */
	public boolean isRequire() default false;

	/**
	 * 校验规则
	 * @return
	 */
	public ValidationType validationType() default ValidationType.None;
	
}
```

提供Excel导入时，Bean对象字段值的校验规则

eg.


```java
/**
	 * 电子邮箱
	 */
	@ValidationRule (validationType = ValidationType.Email,isRequire = true)
	@ExcelMapping (desc="电子邮箱",index=3)
	private String dianziyouxiang  = "" ;
```
导入Excel时,电子邮箱值被要求为必填，并且需要进行Email校验。


**ValueTranslator**

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValueTranslator {

	/**
	 * 实现ITranslator接口的类的名称
	 * @return
	 */
	String implClassName();

}
```

允许自定义字段的翻译工具类(需要实现ITranslator接口)

eg.

```java
	@ValueTranslator ( implClassName = "com.issun.component.hssfworkbook.SexTranslator" )
	@ExcelMapping (index=0,desc="性别")
	SexType sexType;
```


```java
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
```


```java
/**
 * 性别枚举
 */
public enum SexType {
	
	MAN(0),WOMAN(1);
	
	private int type;
	
	SexType(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
	
}
```
以上是自定义一个字段值进行性别翻译实现例子

**2.对外提供使用的API**

`HSSFWorkbookEngine`是对外提供使用的Excel工具类，包含如下工具方法：


**导出**

```java
/**
	 * 将类的字段描述打印为Excel表头.
	 * @param beanClass 类的字节码
	 * @return HSSFWorkbook Excel的Java抽象
	 */
	public static HSSFWorkbook printHeader(Class<?> beanClass)
```

```java
/**
	 * 打印类的Excel表头.
	 * @param beanClass 类的字节码
	 * @param hSSFWorkbook Excel的Java抽象
	 * @param sheetIndex 页签序列{从0开始}
	 * @param headerIndex 打印的表头的序列{从0开始}
	 * @return HSSFWorkbook 打印后的Excel对象
	 */
	public static HSSFWorkbook printHeader(Class<?> beanClass,HSSFWorkbook hSSFWorkbook,
			int sheetIndex,int headerIndex)
```


```java
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
			int sheetIndex,int bodyIndex)
```

**导入**


```java
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
			int sheetIndex,int bodyIndex,int headerIndex)
```

对于导入解析得到的`PreparedBean`对象可以通过PrepareBeanUtil的executeValidate/executeTransform方法分别获取到`校验结果`/`类实例集合`。






