package name.walnut.controller.utils;

import javax.servlet.http.HttpSession;

/**
 * session工具，如果存在session，则可以读取session数据
 * @author:     walnut
 * @version:    1.0
 */
public class SystemContext {
	
	/**
	 * 获得HttpSession中的参数,相当于 session.getAttribute()
	 * @param param session的kdy
	 * @return 如果session不存在，则返回null
	 */
	public static Object getSessionAttribute(String param){
		
		HttpSession session = SessionLocalFilter.sessionLocal.get();
		if(session !=null){
			return session.getAttribute(param);
		}
			
		return null;
	}
	
	/**
	 * 设置session中的变量
	 * @param name 变量名
	 * @param value 值
	 * 
	 * @throws NullPointerException session为空的话抛出异常
	 */
	public static void setSessionAttribute(String name, Object value) {
		
		HttpSession session = SessionLocalFilter.sessionLocal.get();
		if(session == null)
			throw new NullPointerException("session对象为空");
		
		session.setAttribute(name, value);
	}
	
	/**
	 * 移除session中的值
	 * @param param
	 */
	public static void removeSessionAttribute(String param){
		
		HttpSession session = SessionLocalFilter.sessionLocal.get();
		if(session !=null){
			session.removeAttribute(param);
		}
	}
	
	/**
	 * 清空session
	 */
	public static void clearSession() {
		
		HttpSession session = SessionLocalFilter.sessionLocal.get();
		if(session != null) session.invalidate();
	}
}

