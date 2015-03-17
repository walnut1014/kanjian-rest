package name.walnut.common;

public class BusinessException extends RuntimeException {

	public BusinessException(String msg) {
		super(msg);
	}
	
	public BusinessException(String msg, Object data) {
		super(msg);
		this.data = data;
	}
	
	public Object getData() {
		
		return data;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3967735924467519592L;
	
	private Object data;

}
