package name.walnut.controller.utils;

import name.walnut.utils.MD5Utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 用户在线工具类
 * 
 * 用户登录后获得session中的信息
 * @author walnut
 *
 */
public class OnlineUtils {
	
	public static final String Login_USER="loginUser";
	
	/**
	 * 获得当前登录的用户
	 * @return 返回session中的用户
	 */
	public static OnlineUser getOnlineAuth(){
		
		OnlineUser onlineUser = (OnlineUser) SystemContext.getSessionAttribute(Login_USER);
		return onlineUser;
	}
	
	
	/**
	 * 获得登录用户的用户ID
	 * @return 返回session中的用户的ID
	 */
	public static long getOnlineUserId() {
		
		OnlineUser onlineUser = getOnlineAuth();
		if(onlineUser == null)
			throw new NullPointerException("用户未登录！");
		return onlineUser.getId();
	}
	
	/**
	 * 用户是否登录
	 * @return boolean
	 */
	public static boolean isLogin() {
		
		return getOnlineAuth() != null;
	}
	
	public static OnlineUser login(String account, String password) {
		
		//shiro登录
		OnlineUser onLineUser = OnlineUtils.shiroLogin(account.toLowerCase(), MD5Utils.getMD5Sign(password));
		
		SystemContext.setSessionAttribute(Login_USER, onLineUser );
		
		return onLineUser;
	}
	
	/**
	 * shiro登录方法
	 * @param account  账号
	 * @param password 密码
	 * @return OnlineUser
	 */
	private static OnlineUser  shiroLogin(String account, String password) {
		
		try {
				
			UsernamePasswordToken token = new UsernamePasswordToken(account,password);
			
			SecurityUtils.getSubject().login(token);
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(true);
			session.setAttribute("shiroUser", subject);
			OnlineUser onLineUser = (OnlineUser) subject.getPrincipal();
			
			return onLineUser;
			
		} catch (Exception e) {
			
			throw new RuntimeException("登录失败");
		}
	}
	
	/**
	 * 退出登录
	 */
	public static void loginOut() {
		
		SecurityUtils.getSubject().logout();
		SystemContext.removeSessionAttribute(Login_USER);
		SystemContext.clearSession();
	}
}



