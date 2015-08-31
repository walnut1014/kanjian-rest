package name.walnut.auth.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import name.walnut.auth.dto.UserWithMobile;
import name.walnut.auth.entity.User;
import name.walnut.common.BaseDao;
import name.walnut.mapper.auth.UserMapper;

public interface UserDao extends BaseDao<UserMapper> {
	
	/**
	 * 通过ID集合获得用户
	 * @param ids id集合
	 * @return
	 */
	Map<Long, UserWithMobile> findUserByIds(Set<Long> ids);
	
	/**
	 * 通过一堆手机号获得用户查询信息
	 * @param mobilephones
	 * @return
	 */
	List<UserWithMobile> findUserByMobilephones(String[] mobilephones);
	
	User get(long id);
}
