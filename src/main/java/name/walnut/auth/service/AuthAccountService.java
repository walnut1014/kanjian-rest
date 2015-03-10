package name.walnut.auth.service;

public interface AuthAccountService {
	
	/**
	 * 登陆
	 */
	void login();
	
	/**
	 * 注册
	 * @param mobilephone
	 * @param password
	 */
	void register(String mobilephone);
}
