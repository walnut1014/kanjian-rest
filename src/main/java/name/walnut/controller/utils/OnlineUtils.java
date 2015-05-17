package name.walnut.controller.utils;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;
import name.walnut.controller.passport.vo.LoginParam;
import name.walnut.utils.SpringUtils;

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
			throw new BusinessException("用户未登录！");
		return onlineUser.getId();
	}
	
	/**
	 * 用户是否登录
	 * @return boolean
	 */
	public static boolean isLogin() {
		
		return getOnlineAuth() != null;
	}
	
	public static OnlineUser login(LoginParam loginParam) {
		
		AuthAccount authAccount = new AuthAccount();
		authAccount.setMobilephone(loginParam.getMobilephone());
		authAccount.setPassword(loginParam.getPassword());
		
		OnlineUser onLineUser = SpringUtils.getBean(PassportService.class).login(authAccount);
		SystemContext.setSessionAttribute(Login_USER, onLineUser);
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



