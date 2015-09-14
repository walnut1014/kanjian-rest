package name.walnut.common;

public interface BaseDao<T> {
	T get(long id);
	
	long save(T t);	
}
