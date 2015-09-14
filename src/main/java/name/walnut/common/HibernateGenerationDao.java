package name.walnut.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;

public abstract class HibernateGenerationDao<T> {
	
	@SuppressWarnings("unchecked")
	public T get(long id) {
		return (T) getSession().get(getEntityType(), id);
	}
	
	public long save(T t) {
		return (long) getSession().save(t);
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("rawtypes")
	private Class getEntityType() {
		return ResolvableType.forType(this.getClass().getGenericSuperclass()).getGeneric(0).resolve();
	}
	
	@Autowired
	private SessionFactory sessionFactory;
}
