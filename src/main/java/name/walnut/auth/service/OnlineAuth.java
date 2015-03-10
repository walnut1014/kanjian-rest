package name.walnut.auth.service;

public interface OnlineAuth {
	
	/**
	 * 检测一个手机号是否在线
	 * @param mobilephone 待检测手机号
	 * @return
	 */
	boolean isOnline(String mobilephone);
	
	/**
	 * 检测一个手机号是否登录
	 * @param mobilephone
	 * @return
	 */
	boolean isLogin(String mobilephone);
}
