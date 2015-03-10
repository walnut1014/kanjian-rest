package name.walnut.web;

import javax.servlet.ServletContextEvent;

import org.apache.ibatis.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

public class WebContextListener extends ContextLoaderListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
	
		LogFactory.useLog4JLogging();

		initWebApplicationContext(event.getServletContext());
		
	}
	
	public void executeTask() {}
	
	
}
