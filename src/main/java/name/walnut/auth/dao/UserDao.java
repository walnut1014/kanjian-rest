package name.walnut.auth.dao;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.dto.UserWithMobile;
import name.walnut.common.entity.User;
import name.walnut.common.BaseDao;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserDao extends BaseDao<User> {
	
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
	
	/**
	 * 通过手机号和密码获得在线用户
	 * @param authAccount
	 * @return
	 */
	OnlineUser getOnlineUser(String mobilephone, String password);
	
	/**
	 * 通过手机号获得用户对象
	 * @param mobilephone
	 * @return
	 */
	User getUserByMobilephone(String mobilephone);
	
}
