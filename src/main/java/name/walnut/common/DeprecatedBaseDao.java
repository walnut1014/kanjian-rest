package name.walnut.common;

public interface DeprecatedBaseDao<T> {
	
	@Deprecated
	T getMapper();
//	
//	T get();
//	
//	long insert(T t);
}
