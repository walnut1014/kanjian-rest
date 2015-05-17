package name.walnut.auth.service;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.entity.User;

public interface PassportService {
	
	/**
	 * 登陆
	 */
	OnlineUser login(AuthAccount authAccount);
	
	/**
	 * 注册
	 */
	void register(AuthAccount authAccount, User user);
	
	/**
	 * 是否存在手机号
	 * @param mobilephone
	 */
	boolean isExist(String mobilephone);
	
	/**
	 * 设置新密码
	 * @param mobilephone
	 * @param newPassword
	 * @return
	 */
	void setPassword(String mobilephone, String newPassword);
}
