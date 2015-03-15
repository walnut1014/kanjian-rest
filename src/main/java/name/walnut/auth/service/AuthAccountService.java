package name.walnut.auth.service;

import name.walnut.auth.entity.AuthAccount;

public interface AuthAccountService {
	
	/**
	 * 登陆
	 */
	void login(String mobilephone, String password);
	
	/**
	 * 注册
	 */
	void register(AuthAccount authAccount);
	
	/**
	 * 是否存在手机号
	 * @param mobilephone
	 */
	void isExist(String mobilephone);
}
