package name.walnut.web;

import org.apache.ibatis.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

public class WebContextListener extends ContextLoaderListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
	
		LogFactory.useLog4JLogging();

		initWebApplicationContext(event.getServletContext());
		
	}
	
	public void executeTask() {}
	
	
}
