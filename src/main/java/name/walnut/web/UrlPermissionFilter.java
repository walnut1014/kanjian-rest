package name.walnut.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @author walnut
 * 
 */
public class UrlPermissionFilter extends AccessControlFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {

		Subject subject = getSubject(request, response);
		if (subject.isAuthenticated() == false) {
			
			// 异步请求
			if ("ajax".equals(((HttpServletRequest)request).getHeader("AJAX")))
				asynRedirect(response);
			
			else // 同步请求
				synRedirect(request, response);
			
			return false;
		} else {

			return true;
		}
	}
	
	/**
	 * 同步重定向
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void synRedirect(ServletRequest request,
			ServletResponse response) throws IOException { 
		
		// 不是ajax请求,直接重定向登录地址
        WebUtils.issueRedirect(request, response, "/passport/login");
	}
	
	/**
	 * 异步重定向
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void asynRedirect(ServletResponse response) throws IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		((HttpServletResponse) response).setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("{\"code\":\"302\", \"url\":\"/passport/login\"}");
		out.flush();
		out.close();
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

		return false;
	}

}
