package name.walnut.relation.dao;

import name.walnut.common.BaseDao;
import name.walnut.common.DeprecatedBaseDao;
import name.walnut.common.HibernateGenerationDao;
import name.walnut.mapper.relation.FriendMapper;
import name.walnut.common.entity.Friend;

import java.util.Set;

public interface FriendDao extends BaseDao<Friend> {

	/**
	 * 获得一个用户的所有好友的ID
	 * @param userId
	 * @return
	 */
	Set<Long> getFriendIds(long userId);
}
