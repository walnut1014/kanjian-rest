package name.walnut.controller.utils;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;
import name.walnut.controller.passport.vo.LoginParam;
import name.walnut.utils.SpringUtils;
import org.apache.shiro.SecurityUtils;

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
			throw new BusinessException("用户未登录！", ERROR_UN_LONG);
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
		
		OnlineUser onlineUser = SpringUtils.getBean(PassportService.class)
										   .login(loginParam.getMobilephone(), loginParam.getPassword());
		SystemContext.setSessionAttribute(Login_USER, onlineUser);
		DeviceTokenContainer.INSTANCE.putDeviceToken(onlineUser.getId(), loginParam.getDeviceToken());
		return onlineUser;
	}
	
	/**
	 * 退出登录
	 */
	public static void loginOut() {
		
		SecurityUtils.getSubject().logout();
		SystemContext.removeSessionAttribute(Login_USER);
		SystemContext.clearSession();
	}
	
	private static final int ERROR_UN_LONG = 101;
}



