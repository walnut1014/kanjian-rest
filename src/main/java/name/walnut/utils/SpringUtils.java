package name.walnut.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements BeanFactoryAware {
	
	private static BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringUtils.beanFactory = beanFactory;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T) beanFactory.getBean(name);
	}
	
	public static <T> T getBean(Class<T> requiredType){
		return (T) beanFactory.getBean(requiredType);
	}

}
