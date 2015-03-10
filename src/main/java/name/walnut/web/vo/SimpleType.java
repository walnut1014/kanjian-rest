package name.walnut.web.vo;

/**
 * 前端控制器返回简单类型的包装器
 * 
 * 可以包装String,Number,Boolean
 * @author walnut
 *
 */
public final class SimpleType {
	
	private Object value;
	
	public Object getValue() {
		return value;
	}
	
	/**
	 * 创建String的包装类型
	 * @param value
	 * @return SimpleType
	 */
	public SimpleType(String value) {
		this.value = value;
	}
	
	/**
	 * 创建Number的包装类型
	 * @param value
	 * @return SimpleType
	 */
	public SimpleType(Number value) {
		this.value = value;
	}
	
	/**
	 * 创建Boolean的包装类型
	 * @param value
	 * @return SimpleType
	 */
	public SimpleType(Boolean value) {
		this.value = value;
	}
	
}
