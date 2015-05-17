package name.walnut.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import name.walnut.common.BusinessException;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

/**
 * 手机端异常处理类
 * @author walnut
 *
 */
public class ExceptionResolver extends AbstractHandlerExceptionResolver {

	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		logger.debug("请求：【"+request.getPathInfo()+"】ExceptionResolver接受并处理");
		
		ModelAndView mv = new ModelAndView();
		
		mv.setView(new FastJsonJsonView());
		mv.addObject("success",false);
		mv.addObject("message", ex.getMessage());
		
		ex.printStackTrace();
		
		if (ex instanceof BusinessException)
			mv.addObject("data", ((BusinessException)ex).getData());
		else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	
		return mv;
	}
	
	
	private static Logger logger = Logger.getLogger(ExceptionResolver.class);

}
